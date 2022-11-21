package ch3;

import java.util.Random;

public class Season {

	private static Random random = new Random();

	public String getSeason(){
 		//0から11までのランダムな数字を生成
 		int n = random.nextInt(10);
 
 		//ランダムな数字に対応する季節を返す
 		if(n < 3){
 			return "Winter";
 		}else if(n < 6){
 			return "Spring";
 		}else if(n < 9){
 			return "Summer";
 		}else {
 			return "Autumn";
 		}
 	}
 
 }