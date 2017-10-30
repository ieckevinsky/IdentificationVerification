package edu.hitsz.c102c.cnn;

import edu.hitsz.c102c.dataset.Dataset;

public class recognizetest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 String modelName1 = "model/model1.cnn";
//		 CNN cnn1 = CNN.loadModel(modelName1);	
//		 Dataset testset1 = Dataset.load("C:\\Users\\hylanda\\Desktop\\new_test_second_number.format", ",", -1);
//		 cnn1.predict(testset1, "dataset/test11.predict");
		 		 
		 String modelName2 = "model/model2.cnn";
		 CNN cnn2 = CNN.loadModel(modelName2);	
		 Dataset testset2 = Dataset.load("C:\\Users\\hylanda\\Desktop\\new_operator_realtestdata1.format", ",", -1);
		 cnn2.predict(testset2, "dataset/test22.predict");
		 
		 
//		 String modelName3 = "model/model3.cnn";
//		 CNN cnn3 = CNN.loadModel(modelName3);	
//		 Dataset testset3 = Dataset.load("C:\\Users\\hylanda\\Desktop\\new_test_second_number.format", ",", -1);
//		 cnn3.predict(testset3, "dataset/test33.predict");
	}

}
