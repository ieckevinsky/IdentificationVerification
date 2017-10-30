package edu.hitsz.c102c.cnn;

import edu.hitsz.c102c.cnn.CNN.LayerBuilder;
import edu.hitsz.c102c.cnn.Layer.Size;
import edu.hitsz.c102c.dataset.Dataset;
import edu.hitsz.c102c.util.ConcurenceRunner;
import edu.hitsz.c102c.util.TimedTest;
import edu.hitsz.c102c.util.TimedTest.TestTask;

public class RunCNN {

	public static void runCnn() {
		//����һ�����������
		LayerBuilder builder = new LayerBuilder();
		builder.addLayer(Layer.buildInputLayer(new Size(28, 28)));
		builder.addLayer(Layer.buildConvLayer(6, new Size(5, 5)));
		builder.addLayer(Layer.buildSampLayer(new Size(2, 2)));
		builder.addLayer(Layer.buildConvLayer(12, new Size(5, 5)));
		builder.addLayer(Layer.buildSampLayer(new Size(2, 2)));
		builder.addLayer(Layer.buildOutputLayer(2));
		CNN cnn = new CNN(builder,2);
		
		//�������ݼ�
		String fileName = "D:\\��֤��ѵ������\\2.format";
		Dataset dataset = Dataset.load(fileName,",", 784);
		cnn.train(dataset, 50);
		String modelName = "model/model2.cnn";
		cnn.saveModel(modelName);		
		dataset.clear();
		dataset = null;
		
		//Ԥ��
		//CNN cnn = CNN.loadModel(modelName);	
		Dataset testset = Dataset.load("D:\\��֤��ѵ������\\2test.format", ",", -1);
		cnn.predict(testset, "dataset/test2.predict");
	}

	public static void main(String[] args) {

		new TimedTest(new TestTask() {

			@Override
			public void process() {
				runCnn();
			}
		}, 1).test();
		ConcurenceRunner.stop();

	}

}
