package fr.gharrowbm.occhatopbackend.services;

import fr.gharrowbm.occhatopbackend.entities.ChatopUser;
import fr.gharrowbm.occhatopbackend.entities.Rental;
import fr.gharrowbm.occhatopbackend.exceptions.ChatopUserNotFoundException;
import fr.gharrowbm.occhatopbackend.exceptions.RentalNotFoundException;
import fr.gharrowbm.occhatopbackend.exceptions.UnsupportedMediaTypeException;
import fr.gharrowbm.occhatopbackend.models.BaseMessageResponse;
import fr.gharrowbm.occhatopbackend.models.RentalDTO;
import fr.gharrowbm.occhatopbackend.models.RentalPostRequestDTO;
import fr.gharrowbm.occhatopbackend.models.RentalPutRequestDTO;
import fr.gharrowbm.occhatopbackend.repositories.ChatopUserRepository;
import fr.gharrowbm.occhatopbackend.repositories.RentalRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final ChatopUserRepository chatopUserRepository;

    private final String SAVE_DIRECTORY_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "pictures";
    private final File SAVE_DIRECTORY = new File(SAVE_DIRECTORY_PATH);

    @Override
    public List<RentalDTO> getAll() {
        return rentalRepository.findAll()
                .stream()
                .map(this::fromEntity)
                .toList();
    }

    @Override
    public RentalDTO getById(Long id) {
        return rentalRepository.findById(id)
                .map(this::fromEntity)
                .orElse(null);
    }

    @Override
    public BaseMessageResponse create(RentalPostRequestDTO rentalPostRequestDTO, String email) {
        ChatopUser owner = chatopUserRepository.findByEmail(email)
                .orElseThrow(() -> new ChatopUserNotFoundException("User not found with email [" + email + "]"));

        Rental rental = new Rental();
        rental.setName(rentalPostRequestDTO.name());
        rental.setDescription(rentalPostRequestDTO.description());
        rental.setPrice(BigDecimal.valueOf(rentalPostRequestDTO.price()));
        rental.setSurface(rentalPostRequestDTO.surface());
        rental.setPictureUrl(savePicture(rentalPostRequestDTO.picture()));
        rental.setOwner(owner);

        rentalRepository.save(rental);

        return new BaseMessageResponse("Rental created!");
    }

    @Override
    public BaseMessageResponse update(Long id, RentalPutRequestDTO rentalPutRequestDTO, String email) {
        ChatopUser owner = chatopUserRepository.findByEmail(email)
                .orElseThrow(() -> new ChatopUserNotFoundException("User not found with email [" + email + "]"));

        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFoundException("Rental not found with id [" + id + "]"));


        rental.setName(rentalPutRequestDTO.name());
        rental.setDescription(rentalPutRequestDTO.description());
        rental.setPrice(BigDecimal.valueOf(rentalPutRequestDTO.price()));
        rental.setSurface(rentalPutRequestDTO.surface());
        rental.setOwner(owner);

        rentalRepository.save(rental);

        return new BaseMessageResponse("Rental updated!");
    }

    private RentalDTO fromEntity(Rental rental) {
        return new RentalDTO(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice().floatValue(),
                "http://localhost:3001/pictures/" + rental.getPictureUrl(),
                rental.getDescription(),
                rental.getOwner().getId(),
                rental.getCreatedAt().toString(),
                rental.getUpdatedAt().toString()
        );
    }

    private void createDirectoryIfNeeded() {
        if (!SAVE_DIRECTORY.exists()) {
            SAVE_DIRECTORY.mkdirs();
        }
    }

    private String savePicture(MultipartFile picture) {
        String contentType = picture.getContentType();
        if(!contentType.startsWith("images/")) throw new UnsupportedMediaTypeException("Only images are supported");
        String randomUUID = UUID.randomUUID().toString();
        String pictureName = randomUUID + "-" + picture.getOriginalFilename();
        String picturePath = SAVE_DIRECTORY_PATH + File.separator + pictureName;
        try {
            createDirectoryIfNeeded();
            File fileToSave = new File(picturePath);
            ImageIO.write(ImageIO.read(picture.getInputStream()), "jpg", fileToSave);

            return pictureName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
