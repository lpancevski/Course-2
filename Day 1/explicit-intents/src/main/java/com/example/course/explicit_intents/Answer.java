package com.example.course.explicit_intents;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {

    private int mOrderNumber;
    private Boolean mIsCorrect;
    private String mText;
    
    public Answer(Parcel in) {
        mOrderNumber = in.readInt();
        mIsCorrect = (Boolean) in.readValue(Boolean.class.getClassLoader());
        mText = in.readString();
    }
    
    public Answer(int orderNumber, Boolean isCorrect, String text) {
        mOrderNumber = orderNumber;
        mIsCorrect = isCorrect;
        mText = text;
    }
    
    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel source) {
            return new Answer(source);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
    
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeInt(mOrderNumber);
        dest.writeValue(mIsCorrect);
        dest.writeString(mText);
	}

	public int getmOrderNumber() {
		return mOrderNumber;
	}

	public void setmOrderNumber(int mOrderNumber) {
		this.mOrderNumber = mOrderNumber;
	}

	public Boolean getmIsCorrect() {
		return mIsCorrect;
	}

	public void setmIsCorrect(Boolean mIsCorrect) {
		this.mIsCorrect = mIsCorrect;
	}

	public String getmText() {
		return mText;
	}

	public void setmText(String mText) {
		this.mText = mText;
	}

}
