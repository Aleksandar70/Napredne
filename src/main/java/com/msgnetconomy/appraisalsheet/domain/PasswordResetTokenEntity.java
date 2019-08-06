package com.msgnetconomy.appraisalsheet.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "password_reset_token", schema = "projectevaluation")
public class PasswordResetTokenEntity {

    private int passwordResetTokenId;
    private String token;
    private UserEntity user;
    private Date expiryDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_reset_tokenID")
    public int getPasswordResetTokenId() {
        return passwordResetTokenId;
    }

    public void setPasswordResetTokenId(int passwordResetTokenId) {
        this.passwordResetTokenId = passwordResetTokenId;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Basic
    @Column(name = "expiry_date")
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "PasswordResetTokenEntity{" +
                "passwordResetTokenId=" + passwordResetTokenId +
                ", token='" + token + '\'' +
                ", user=" + user +
                ", expiryDate=" + expiryDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordResetTokenEntity that = (PasswordResetTokenEntity) o;
        return passwordResetTokenId == that.passwordResetTokenId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passwordResetTokenId);
    }
}
