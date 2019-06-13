package br.com.bakingapp.services.recipeService.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Step implements Parcelable {
    @Json(name = "id")
    private final int id;

    @Json(name = "shortDescription")
    private final String shortDescription;

    @Json(name = "description")
    private final String description;

    @Json(name = "videoURL")
    private final String videoURL;

    @Json(name = "thumbnailURL")
    private final String thumbnailURL;

    public Step(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    protected Step(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public String getShortDescription() {
        return shortDescription;
    }
}
