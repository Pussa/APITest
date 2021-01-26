package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FactsInformation {
    @SerializedName("text")
    public String fact;
    @SerializedName("_id")
    public String factId;
}