package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class User {
    @SerializedName("name")
    public static Name name;
}
