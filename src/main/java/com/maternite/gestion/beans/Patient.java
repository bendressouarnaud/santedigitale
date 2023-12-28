package com.maternite.gestion.beans;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "anf_patient")
public class Patient {

    @Id
    @Column(name = "idpat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpat;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "age")
    private int age;

    @Column(name = "profession")
    private int profession;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "identifiant")
    private String identifiant;

    @Column(name = "provenance")
    private int provenance;

    @Column(name = "haswallet")
    private int haswallet;

    //------------------------------
    @ColumnTransformer(
        read = "cast(aes_decrypt(nom, 'K8_jemange') as char(255))",
        write ="aes_encrypt(?, 'K8_jemange')"
    )
    @Column(name = "nom")
    private String nom;

    @ColumnTransformer(
            read = "cast(aes_decrypt(prenom, 'K8_jemange') as char(255))",
            write ="aes_encrypt(?, 'K8_jemange')"
    )
    @Column(name = "prenom")
    private String prenom;

    @ColumnTransformer(
            read = "cast(aes_decrypt(cni, 'K8_jemange') as char(255))",
            write ="aes_encrypt(?, 'K8_jemange')"
    )
    @Column(name = "cni")
    private String cni;

    @ColumnTransformer(
            read = "cast(aes_decrypt(telephone, 'K8_jemange') as char(255))",
            write ="aes_encrypt(?, 'K8_jemange')"
    )
    @Column(name = "telephone")
    private String telephone;

    @ColumnTransformer(
            read = "cast(aes_decrypt(email, 'K8_jemange') as char(255))",
            write ="aes_encrypt(?, 'K8_jemange')"
    )
    @Column(name = "email")
    private String email;

    @ColumnTransformer(
            read = "cast(aes_decrypt(societe, 'K8_jemange') as char(255))",
            write ="aes_encrypt(?, 'K8_jemange')"
    )
    @Column(name = "societe")
    private String societe;

    @Column(name = "nomjeunefille")
    private String nomjeunefille;

    @Column(name = "datenaissance")
    private Date datenaissance;

    @Column(name = "lieunaissance")
    private String lieunaissance;

    @Column(name = "nompere")
    private String nompere;

    @Column(name = "nommere")
    private String nommere;

    @Column(name = "residence")
    private String residence;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "contactpere")
    private String contactpere;

    @Column(name = "contactmere")
    private String contactmere;

    @Column(name = "repondant")
    private String repondant;

    @Column(name = "contactrepondant")
    private String contactrepondant;

    @Column(name = "groupesanguin")
    private int groupesanguin;

    @Column(name = "datecreation")
    private Date datecreation;


    // M e t h o d s :
    public Patient() {
    }

    public int getIdpat() {
        return idpat;
    }

    public void setIdpat(int idpat) {
        this.idpat = idpat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getProfession() {
        return profession;
    }

    public void setProfession(int profession) {
        this.profession = profession;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public int getProvenance() {
        return provenance;
    }

    public void setProvenance(int provenance) {
        this.provenance = provenance;
    }

    public int getHaswallet() {
        return haswallet;
    }

    public void setHaswallet(int haswallet) {
        this.haswallet = haswallet;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getNomjeunefille() {
        return nomjeunefille;
    }

    public void setNomjeunefille(String nomjeunefille) {
        this.nomjeunefille = nomjeunefille;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getLieunaissance() {
        return lieunaissance;
    }

    public void setLieunaissance(String lieunaissance) {
        this.lieunaissance = lieunaissance;
    }

    public String getNompere() {
        return nompere;
    }

    public void setNompere(String nompere) {
        this.nompere = nompere;
    }

    public String getNommere() {
        return nommere;
    }

    public void setNommere(String nommere) {
        this.nommere = nommere;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getContactpere() {
        return contactpere;
    }

    public void setContactpere(String contactpere) {
        this.contactpere = contactpere;
    }

    public String getContactmere() {
        return contactmere;
    }

    public void setContactmere(String contactmere) {
        this.contactmere = contactmere;
    }

    public String getRepondant() {
        return repondant;
    }

    public void setRepondant(String repondant) {
        this.repondant = repondant;
    }

    public String getContactrepondant() {
        return contactrepondant;
    }

    public void setContactrepondant(String contactrepondant) {
        this.contactrepondant = contactrepondant;
    }

    public int getGroupesanguin() {
        return groupesanguin;
    }

    public void setGroupesanguin(int groupesanguin) {
        this.groupesanguin = groupesanguin;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }
}
