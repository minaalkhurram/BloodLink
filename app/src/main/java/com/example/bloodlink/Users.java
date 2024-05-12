package com.example.bloodlink;

public class Users {
  private   String username;



  private String email;
  private  String bloodType;
  private String password;

  private long conatactNum;

  private Boolean medicalDisease;

  private int age;

  Users(String mail,String u , String bt, String pass, Boolean md, int age, long cn){
    this.username=u;
    this.bloodType=bt;
    this.password=pass;
    this.medicalDisease=md;
    this.conatactNum=cn;
    this.age=age;
    this.email=mail;
  }

  public String getEmail()
  {
    return this.email;

  }

  public String getUser()
  {
    return this.username;
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getBloodType()
  {
    return this.bloodType;
  }

  public int getAge()
  {
    return this.age;
  }

  public boolean getmedicalDisease()
  {
    return this.medicalDisease;

  }

  public long getConatactNum()
  {
    return  this.conatactNum;
  }

}
