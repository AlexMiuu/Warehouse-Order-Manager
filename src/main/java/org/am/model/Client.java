package org.am.model;

import java.sql.Date;


/**
 * A class representing a client in the system.
 *

 */
public class Client {
    /**
     * The unique identifier of the client.
     */
    private int id;
    /**
     * The first name of the client.
     */
    private String firstName;
    /**
     * The last name of the client.
     */
    private String lastName;
    /**
     * The date of birth of the client.
     */
    private Date dob;
    /**
     * The email address of the client.
     */
    private String email;
    /**
     * The phone number of the client.
     */
    private String phoneNumber;

    public Client() {
    }

    public Client(String firstName, String lastName, Date dob, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
