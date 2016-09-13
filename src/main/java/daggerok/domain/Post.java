package daggerok.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by mak on 9/13/16.
 */
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Post implements Serializable {

    @Id @GeneratedValue Long id;
    @NonNull String text;
}
