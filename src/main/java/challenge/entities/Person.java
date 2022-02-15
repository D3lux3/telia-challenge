package challenge.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.validation.constraints.NotEmpty;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person extends AbstractPersistable<Long> {

    @NotEmpty
    @Size(min = 4, max = 64, message = "Name has to be between 4 and 64 letters long")
    private String name;

    private String address;
    private String phoneNumber;

    @CreationTimestamp
    private Instant createdDate;
}
