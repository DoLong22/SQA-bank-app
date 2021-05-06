package com.example.bank.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
@Component
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
//    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "password")
//    @Email(message = "Email should be valid")
    private String password;

    @Column(name = "masokh")
//    @NotNull(message = "Customer code is require")
    private String masoKh;

    @Column(name="hoten")
//    @NotBlank(message = "First name cannot be blank")
    private String hoTen;

    @Column(name = "ngaysinh")
//    @NotNull(message = "Date of birth is require")
    private String ngaySinh;

    @Column(name="gioi_tinh")
//    @NotBlank(message = "Gender cannot be blank")
    private String gioiTinh;

    @Column(name="quoc_tich")
//    @NotBlank(message = "First name cannot be blank")
    private String quocTich;

    @Column(name="quequan")
    private String queQuan;

    @Column(name = "socmt")
//    @NotNull(message = "Card number is require")
    private String soCmt;

    @Column(name = "ngaycap")
//    @NotNull(message = "Card number is require")
    private String ngayCap;

    @Column(name = "noicap")
    private String noiCap;

    @Column(name="diachi")
//    @NotBlank(message = "Home number cannot be blank")
    private String diaChi;

    @Column(name = "sodienthoai")
    private String soDt;

    @Column(name = "tinh_trang_hon_nhan")
    private String ttHonnhan;

    @Column(name = "congviec")
    private String congViec;

    @Column(name = "tencongty")
    private String congTy;

    @Column(name = "phongban")
    private String phongBan;

    @Column(name = "thu_nhap")
    private float thuNhap;

    @Column(name = "tennguoithan1")
    private String tenNguoiThan1;

    @Column(name = "diachinguoithan1")
    private String diaChiNguoiThan1;

    @Column(name = "sdtnguoithan1")
    private String sdtNguoiThan1;

    @Column(name = "tennguoithan2")
    private String tenNguoiThan2;

    @Column(name = "diachinguoithan2")
    private String diaChiNguoiThan2;

    @Column(name = "sdtnguoithan2")
    private String sdtNguoiThan2;

    @Column(name="cam_ket")
    private boolean camKet;


}