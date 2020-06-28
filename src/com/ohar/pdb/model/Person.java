package com.ohar.pdb.model;

import com.ohar.pdb.model.enums.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private Long id;
    private String lastName;
    private String firstName;
    private String fatherName;
    private String fio;
    private LocalDate birthday;
    private String education;
    private String profession;
    private FamilyStatus familyStatus;
    private Gender gender;
    private String city;
    private String street;
    private String numberHouse;
    private String numberFlat;
    private String address;
    private String telephon;
    private String mobile;
    private Boolean hrost = false;
    private LocalDate hrostDate;
    private Boolean kamuniya = false;
    private LocalDate kamuniyaDate;
    private Boolean kanfirmazyia = false;
    private LocalDate kanfirmazyiaDate;
    private Boolean suzhenstva = false;
    private LocalDate suzhenstvaDate;
    private ReligionEducation religionEducation;
    private UdzelImshy udzelImshy;
    private Spovedz spovedz;
    private Person partner;
    private List<String> children = new ArrayList<>();
    private List<String> otherMember = new ArrayList<>();
    private String comments;
    private List<String> visitYears = new ArrayList<>();
    private String lastVisitYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        updateFio();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        updateFio();
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
        updateFio();
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
    private void updateFio() {
        this.fio = lastName + " " + firstName + " " + fatherName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public FamilyStatus getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(FamilyStatus familyStatus) {
        this.familyStatus = familyStatus;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        updateAddress();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
        updateAddress();
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
        updateAddress();
    }

    public String getNumberFlat() {
        return numberFlat;
    }

    public void setNumberFlat(String numberFlat) {
        this.numberFlat = numberFlat;
        updateAddress();
    }

    private void updateAddress() {
        this.address = city + ", " + street + " " + numberHouse + "/" + numberFlat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephon() {
        return telephon;
    }

    public void setTelephon(String telephon) {
        this.telephon = telephon;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getHrost() {
        return hrost;
    }

    public void setHrost(Boolean hrost) {
        this.hrost = hrost;
    }

    public LocalDate getHrostDate() {
        return hrostDate;
    }

    public void setHrostDate(LocalDate hrostDate) {
        this.hrostDate = hrostDate;
    }

    public Boolean getKamuniya() {
        return kamuniya;
    }

    public void setKamuniya(Boolean kamuniya) {
        this.kamuniya = kamuniya;
    }

    public LocalDate getKamuniyaDate() {
        return kamuniyaDate;
    }

    public void setKamuniyaDate(LocalDate kamuniyaDate) {
        this.kamuniyaDate = kamuniyaDate;
    }

    public Boolean getKanfirmazyia() {
        return kanfirmazyia;
    }

    public void setKanfirmazyia(Boolean kanfirmazyia) {
        this.kanfirmazyia = kanfirmazyia;
    }

    public LocalDate getKanfirmazyiaDate() {
        return kanfirmazyiaDate;
    }

    public void setKanfirmazyiaDate(LocalDate kanfirmazyiaDate) {
        this.kanfirmazyiaDate = kanfirmazyiaDate;
    }

    public Boolean getSuzhenstva() {
        return suzhenstva;
    }

    public void setSuzhenstva(Boolean suzhenstva) {
        this.suzhenstva = suzhenstva;
    }

    public LocalDate getSuzhenstvaDate() {
        return suzhenstvaDate;
    }

    public void setSuzhenstvaDate(LocalDate suzhenstvaDate) {
        this.suzhenstvaDate = suzhenstvaDate;
    }

    public ReligionEducation getReligionEducation() {
        return religionEducation;
    }

    public void setReligionEducation(ReligionEducation religionEducation) {
        this.religionEducation = religionEducation;
    }

    public UdzelImshy getUdzelImshy() {
        return udzelImshy;
    }

    public void setUdzelImshy(UdzelImshy udzelImshy) {
        this.udzelImshy = udzelImshy;
    }

    public Spovedz getSpovedz() {
        return spovedz;
    }

    public void setSpovedz(Spovedz spovedz) {
        this.spovedz = spovedz;
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public List<String> getOtherMember() {
        return otherMember;
    }

    public void setOtherMember(List<String> otherMember) {
        this.otherMember = otherMember;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<String> getVisitYears() {
        return visitYears;
    }

    public void setVisitYears(List<String> visitYears) {
        this.visitYears = visitYears;
    }

    public String getLastVisitYear() {
        return lastVisitYear;
    }

    public void setLastVisitYear(String lastVisitYear) {
        this.lastVisitYear = lastVisitYear;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", education='" + education + '\'' +
                ", profession='" + profession + '\'' +
                ", familyStatus='" + familyStatus + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberHouse='" + numberHouse + '\'' +
                ", numberFlat='" + numberFlat + '\'' +
                ", telephon='" + telephon + '\'' +
                ", mobile='" + mobile + '\'' +
                ", hrost='" + hrost + '\'' +
                ", hrostDate='" + hrostDate + '\'' +
                ", kamuniya='" + kamuniya + '\'' +
                ", kamuniyaDate='" + kamuniyaDate + '\'' +
                ", kanfirmazyia='" + kanfirmazyia + '\'' +
                ", kanfirmazyiaDate='" + kanfirmazyiaDate + '\'' +
                ", suzhenstva='" + suzhenstva + '\'' +
                ", suzhenstvaDate='" + suzhenstvaDate + '\'' +
                ", religionEducation='" + religionEducation + '\'' +
                ", udzelImshy='" + udzelImshy + '\'' +
                ", spovedz='" + spovedz + '\'' +
                ", partner='" + (partner != null ? partner.getId() : null) + '\'' +
                ", children='" + children + '\'' +
                ", otherMember='" + otherMember + '\'' +
                ", comments='" + comments + '\'' +
                ", lastVisitYear='" + lastVisitYear + '\'' +
                ", visitYears='" + visitYears + '\'' +
                '}';
    }
}
