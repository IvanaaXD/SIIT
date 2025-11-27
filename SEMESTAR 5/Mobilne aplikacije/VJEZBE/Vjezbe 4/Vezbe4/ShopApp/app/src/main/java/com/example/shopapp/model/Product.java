package com.example.shopapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    private Long id;
    private String title;
    private String description;
    private int image;

    public Product(Long id, String title, String description, int image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public Product() {
    }
    // Konstruktor za čitanje iz Parcel objekta
    protected Product(Parcel in) {
        // Čitanje ostalih atributa proizvoda iz Parcel objekta
        id = in.readLong();
        title = in.readString();
        description = in.readString();
        image = in.readInt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
    /*
    * Ova metoda opisuje vrste posebnih objekata koje vaša Parcelable implementacija sadrži.
    * Većinom se vraća 0, osim u slučajevima kada objekat uključuje File Descriptor,
    * u kom slučaju se vraća 1.*/
    @Override
    public int describeContents() {
        return 0;
    }
    /*
    * Metoda koja uzima dva argumenta: Parcel u koji se vaš objekat serijalizuje i
    * flags koje Android koristi za označavanje načina na koji se objekat treba
    * serijalizovati. U ovoj metodi trebate upisati sve potrebne podatke iz vašeg
    * objekta u Parcel.
    * */
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(image);
    }
    /*
    * Da biste omogućili Androidu da regeneriše vaš objekat iz Parcel-a,
    * morate da obezbedite statički CREATOR polje koje implementira Parcelable.Creator
    * interfejs. Ovaj interfejs ima dve metode:
    * - createFromParcel(Parcel source): Stvara i vraća novu instancu vaše klase,
    * popunjavajući je podacima iz Parcel objekta koji je prosleđen kao argument.
    * - newArray(int size): Vraća niz vaše klase, što Android koristi kada se
    * regenerišu nizovi Parcelable objekata.
    * */
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
