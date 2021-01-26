package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Name {
    @SerializedName("first")
    public static String first;
    @SerializedName("last")
    public static String last;
}
