package com.challenge.alura.AluraFlix.core.entities.profiles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Document(collection = "collection_profile")
public class Profile implements GrantedAuthority {
    @Id
    private String id;
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
