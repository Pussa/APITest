package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Fact {
    @SerializedName("user")
    public static User user;
}
