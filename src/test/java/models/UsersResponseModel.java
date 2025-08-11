package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UsersResponseModel {
    Integer page;
    Integer perPage;
    Integer total;
    Integer totalPages;

    List<UserDataModel> data;
    Support support;

    @Data
    public static class UserDataModel { // This class won't inherit @JsonNaming annotation
        private Integer id;
        private String email;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String avatar;
    }

    @Data
    public static class Support {
        private String url;
        private String text;
    }
}
