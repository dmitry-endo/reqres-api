package api.models;

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
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class UserDataModel {
        private Integer id;
        private String email;
        private String firstName;
        private String lastName;
        private String avatar;
    }

    @Data
    public static class Support {
        private String url;
        private String text;
    }
}
