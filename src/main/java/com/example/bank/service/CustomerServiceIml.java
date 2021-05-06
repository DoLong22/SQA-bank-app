package com.example.bank.service;

import com.example.bank.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerServiceIml implements CustomerService {
    private static ArrayList<Customer> customers = new ArrayList<Customer>();

    static {
        customers.add(new Customer(1,"email", "password", "masoKh", "hoTen", "ngaySinh", "gioiTinh",
                "quocTich", "queQuan","soCmt", "ngayCap","noiCap", "diaChi","soDt", "ttHonnhan","congViec","congTy"
                ,"phongBan",1,"tenNguoiThan1"
        ,"diaChiNguoiThan1","sdtNguoiThan1","tenNguoiThan2","diaChiNguoiThan2","sdtNguoiThan2",true));
        customers.add(new Customer(1,"abc@gmail", "123456", "123", "Nguyen Van A", "ngaySinh", "Nam",
                "VietNam", "HaNoi","123", "ngayCap","HaNoi", "NguyenTrai","123", "Doc than","Ke toan","cong Ty A"
                ,"phongBan",1,"tenNguoiThan1"
                ,"diaChiNguoiThan1","sdtNguoiThan1","tenNguoiThan2","diaChiNguoiThan2","sdtNguoiThan2",true));
        customers.add(new Customer(3,"email", "password", "masoKh", "hoTen", "ngaySinh", "gioiTinh",
                "quocTich", "queQuan","soCmt", "ngayCap","noiCap", "diaChi","soDt", "ttHonnhan","congViec","congTy"
                ,"phongBan",1,"tenNguoiThan1"
                ,"diaChiNguoiThan1","sdtNguoiThan1","tenNguoiThan2","diaChiNguoiThan2","sdtNguoiThan2",true));
        customers.add(new Customer(1,"abc@gmail", "123456", "123", "Vu Van A", "ngaySinh", "Nam",
                "VietNam", "HaNoi","123", "ngayCap","HaNoi", "NguyenTrai","123", "Doc than","Ke toan","cong Ty A"
                ,"phongBan",1,"tenNguoiThan1"
                ,"diaChiNguoiThan1","sdtNguoiThan1","tenNguoiThan2","diaChiNguoiThan2","sdtNguoiThan2",true));
    }
    @Override
    public Customer regisCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }


    @Override
    public List<Customer> getCustomerList(){
        return customers;
    }

    @Override
    public Customer getCustomerById(int id){
        for(Customer customer : customers){
            if (customer.getId()==id){
                return customer;
            }
        }
        return null;
    }

    @Override
    public List<Customer> searchCustomers(String keyword){
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers){
            if (customer.getHoTen().contains(keyword)){
                result.add(customer);
            }
        }
        return result;
    }
}
