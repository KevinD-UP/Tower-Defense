package Datamodel;

//manage the creation of enemy using an interval of time

public class Wave{
	private int  spawnTime;//interval of second to create an enemy
	private int totalSecond;//time spent at the applicationFX
	private int[] list;//used to check the size off all we created
	private int position;//position de l'ennemi a creer
	private boolean end;
	private boolean shouldCreate;

	/**
	 *
	 * @param spawnTime
	 * @param list
	 */
	public Wave(int spawnTime, int[] list){
		this.spawnTime = spawnTime;
		this.list = list;
		this.end = false;
		this.shouldCreate = false;
	}



	/**
	 * call the createEnemy when it's time to do so
	 */
	public void setWave(){
		if(totalSecond % spawnTime == 0){
			createEnemy();

		}else{
			this.shouldCreate = false;
		}
	}


	/**
	 * this allows the wave class give a signal to create a enemy if
	 */
	private void createEnemy(){
		if(this.position<list.length) {
			this.shouldCreate = true;
		}else{
			this.end = true;
		}
	}



	/**
	 * to pass the time spent at the applicationFx
	 * @param total
	 */
	public void setTotalSecond(int total){
		totalSecond = total;
		setWave();
	}



	/**
	 * to check if we reach the max size
	 * @return
	 */
	public boolean getEnd(){
		return this.end;
	}



	/**
	 * get the signal
	 * @return
	 */
	public boolean isShouldCreate() {
		return shouldCreate;
	}

	/**
	 *
	 * @return
	 */
	public int enemy() {
		int enemy = this.list[this.position];
		this.position ++;
		return enemy;
	}

	/**
	 *
	 * @param i
	 * @return
	 * @throws Exception
	 */
	public static Wave[] createWaves(int i) throws Exception {
		Wave[] tmp = null;

		switch(i) {
		case 0 :
			tmp = new Wave[200];
			for(int j=0;j<tmp.length;j++) {
				tmp[j] = randomWave();
			}
			break;
		case 1 :
			tmp = new Wave[2];
			int[] t1 = {0,0,0,1,0,0};
			tmp[0] = new Wave(3, t1);
			int[] u1 = {1,0,1,0,1,0,1,0};
			tmp[1] = new Wave(3, u1);
			break;

		case 2 :
			tmp = new Wave[3];
			int[] t2 = {0,0,0,1,1,1,1,1,1};
			tmp[0] = new Wave(3, t2);
			int[] u2 = {1,0,1,0,1,0,1,1,0,2};
			tmp[1] = new Wave(3, u2);
			int[] v2 = {1,0,1,0,1,2,1,2};
			tmp[2] = new Wave(3, v2);	
			break;
		
		case 3 :
			tmp = new Wave[3];
			int[] t3 = {0,0,0,1,1,1,1,1};
			tmp[0] = new Wave(3, t3);
			int[] u3 = {1,0,1,0,1,0,1,0,1,0,1,2,2,2};
			tmp[1] = new Wave(3, u3);
			int[] v3 = {1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,3};
			tmp[2] = new Wave(3, v3);
			break;
			
		case 4 :
			tmp = new Wave[4];
			int[] t4 = {0,0,0,1,1,1,1,1};
			tmp[0] = new Wave(3, t4);
			int[] u4 = {1,0,1,0,1,0,1,0,1,2,1,2,1};
			tmp[1] = new Wave(3, u4);
			int[] v4 = {0,0,0,1,2,2,2,1,2};
			tmp[2] = new Wave(3, v4);
			int[] w4 = {3};
			tmp[3] = new Wave(3, w4);
			break;
			
		case 5 :
			tmp = new Wave[5];
			int[] t5 = {0,0,0,1,0,0,0,2,1,1};
			tmp[0] = new Wave(2, t5);
			int[] u5 = {1,0,1,0,1,0,1,1,1,2,0};
			tmp[1] = new Wave(2, u5);
			int[] v5 = {0,0,0,1,2,2,1,2,2,2,1,1};
			tmp[2] = new Wave(2, v5);
			int[] w5 = {1,0,1,0,1,0,1,0,2,0,2,2,1,1,1,1,1,0,0};
			tmp[3] = new Wave(2, w5);
			int[] x5 = {3,3};
			tmp[4] = new Wave(2, x5);
			break;
			
		case 6 :
			tmp = new Wave[6];
			int[] t6 = {0,0,0,1,1,1,1,1,2,2};
			tmp[0] = new Wave(2, t6);
			int[] u6 = {1,0,1,0,1,0,1,0,3,2,2,2};
			tmp[1] = new Wave(2, u6);
			int[] v6 = {0,0,0,1,1,1,2,2,2,3,2};
			tmp[2] = new Wave(2, v6);
			int[] w6 = {1,0,1,0,1,0,1,0,3,3,1,1,2,2,2,2};
			tmp[3] = new Wave(2, w6);
			int[] x6 = {0,0,0,1,2,2,2,2,1,2};
			tmp[4] = new Wave(2, x6);
			int[] y6 = {2,2,2,2,3,3,3};
			tmp[5] = new Wave(2, y6);
			break;
			
		case 7 :
			tmp = new Wave[6];
			int[] t7 = {0,0,0,1,1,1,1,1,0};
			tmp[0] = new Wave(1, t7);
			int[] u7 = {1,0,1,0,1,0,1,0,1,1,1,2,2,2};
			tmp[1] = new Wave(1, u7);
			int[] v7 = {0,0,0,1,2,2,2,3,3,3,2,1,1,1,1};
			tmp[2] = new Wave(1, v7);
			int[] w7 = {1,0,1,0,1,0,1,0,2,2,2,3,3,3,3};
			tmp[3] = new Wave(1, w7);
			int[] x7 = {0,0,0,1,2,2,2,2,2,2,2};
			tmp[4] = new Wave(1, x7);
			int[] y7 = {3,3,3,3,3,3,3,2,2,2};
			tmp[5] = new Wave(1, y7);
			break;
			
		case 8 :
			tmp = new Wave[6];
			int[] t8 = {0,0,0,1,1,1,2,2,1,1};
			tmp[0] = new Wave(1, t8);
			int[] u8 = {1,0,1,0,1,0,1,0,2,3,2};
			tmp[1] = new Wave(1, u8);
			int[] v8 = {0,0,0,1,2,2,2,3,3};
			tmp[2] = new Wave(1, v8);
			int[] w8 = {1,0,1,0,1,0,1,0,2,2,3,3,3,3};
			tmp[3] = new Wave(1, w8);
			int[] x8 = {0,0,0,1,2,3,3,2,2,2,2,2,2,2,2};
			tmp[4] = new Wave(1, x8);
			int[] y8 = {2,2,2,2,2,2,2,2,2,3,3,3,3,3};
			tmp[5] = new Wave(1, y8);
			break;
			
		case 9 :
			tmp = new Wave[6];
			int[] t9 = {0,0,0,1,1,1,1,1,2,2};
			tmp[0] = new Wave(1, t9);
			int[] u9 = {1,2,1,1,1,1,2,3,2,2};
			tmp[1] = new Wave(1, u9);
			int[] v9 = {1,1,1,1,1,2,2,2,2,2,2};
			tmp[2] = new Wave(1, v9);
			int[] w9 = {2,2,2,2,2,2,2,2,3,3,3,3};
			tmp[3] = new Wave(1, w9);
			int[] x9 = {2,3,2,3,2,3,2,3,2};
			tmp[4] = new Wave(1, x9);
			int[] y9 = {3,3,3,3,3,3,3,3,3,3,3,3};
			tmp[5] = new Wave(1, y9);
			break;
			
		case 10 :
			tmp = new Wave[6];
			int[] t10 = {0,0,0,1,1,0,1,0,1,0,1,0,2,2};
			tmp[0] = new Wave(1, t10);
			int[] u10 = {1,0,1,0,1,0,1,0,1,1,1,2,2,2,2,3,3,3,3};
			tmp[1] = new Wave(1, u10);
			int[] v10 = {0,0,0,1,2,2,2,2,2,3,3,3,3};
			tmp[2] = new Wave(1, v10);
			int[] w10 = {1,0,1,0,1,0,1,0,2,2,2,2,2,3,3,3,3,3,3};
			tmp[3] = new Wave(1, w10);
			int[] x10 = {2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3};
			tmp[4] = new Wave(1, x10);
			int[] y10 = {1,0,1,0,1,0,1,0,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,4};
			tmp[5] = new Wave(1, y10);
			break;
		
		default : 
			throw new Exception("Impossible de creer cette wave " + i );
		}
		

		return tmp;
	}

	/**
	 *
	 * @return
	 */
	private static Wave randomWave() {
		int r = (int)( Math.random()*100);
		int[] tab = new int[r];
		double proba = Math.random();
		for(int i=0;i<r;i++)
		{
			if(proba < 0.2) {
				tab[i] = 0;
			}
			else if(proba < 0.4)
			{
				tab[i] = 1;
			}
			else if(proba < 0.6){
				tab[i] = 2;
			}
			else if(proba < 0.9){
				tab[i] = 3;
			}
			else {
				tab[i] = 4;
			}
		}
		Wave w = new Wave(1,tab);
		return w;
	}
}