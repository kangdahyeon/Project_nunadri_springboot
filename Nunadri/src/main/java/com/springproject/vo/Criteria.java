package com.springproject.vo;

import lombok.Data;

@Data
public class Criteria {
   private int pageNum;
   private int amount;
   private int startNum;
   
   public Criteria() {
      //객체 생성시 기본 생성자 호출 시 매개변수를 줘서 매개변수를 가지고 있는 생성자 함수 호출
      this(1, 8);
   }
   
   public Criteria(int pageNum, int amount) {
      this.pageNum = pageNum;
      this.amount = amount;
      
   }
   
   @Override
   public String toString() {
      return "Criteria [pageNum=" + pageNum + ", amount=" + amount + "]";
   }

}