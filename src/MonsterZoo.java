
import java.util.ArrayList;
import java.util.List;

public class MonsterZoo {
	double distance=0.0;//歩いた距離
	int balls=10;//モンスターを捕まえられるボールの数
	int fruits=0;//ぶつけるとモンスターが捕まえやすくなるフルーツ

	//卵は最大9個まで持てる．卵を取得するとeggにtrueが代入され，
	//移動するたびに,eggDistanceに1.0kmずつ加算される．
	//3km移動するとランダムでモンスターが孵る
	double eggDistance[] = new double[9];
	boolean egg[] = new boolean[9];

	//ユーザがGetしたモンスター一覧
	String userMonster[] = new String[100];

	//モンスター図鑑．モンスターの名前とレア度(0.0~9.0)がそれぞれの配列に保存されている
	//レア度が高いほうが捕まえにくい
	String monsterZukan[] = new String[22];
	double monsterRare[] = new double[22];
	

	//呼び出すと1km distanceが増える
	/**
	 * 
	 */
	void move(){
		this.distance++;
		int c = 0;
		for (boolean s: this.egg){//卵は移動距離が進むと孵化するため，何km移動したかを更新する
			if(s==true){
				this.eggDistance[c]++;
			}
			c++;
		}
		//forをstreamに書き換える時、インデックスをどう扱えばいいかわからない。
		//for文の時はカウント用の変数が存在するので、ループの主となる配列またはリスト（上記ではegg）以外の配列（eggDistance）のインデックスとして使用できると思う。
		//しかし、Streamを使用した場合カウント用の変数が存在しないので、どのようにすればいいかわからなかった。
		//↓↓↓streamを使用しようとして諦めました↓↓↓
		// List<Boolean> eggList = new ArrayList<>();
		// for(boolean n : eggList) {
		// 	eggList.add(n);
		// }
		// eggList.stream().filter((i) -> { return i = true; }).forEach((i) -> {this.eggDistance[i]++; });

		int flg1 = (int)(Math.random()*10);//0,1の場合はズーstation，7~9の場合はモンスター
		if(flg1<=1){
			zooStation(balls, fruits);
		}else if(flg1>=7){
			encountMonster();
		}
		List<String> userMonsterList = new ArrayList<>();
		for(String n : userMonsterList) {
			userMonsterList.add(n);
		}
		//userMonsterList.stream().filter((i) -> { return i != null; }).forEach((i) -> {this.userMonster[j]=this.monsterZukan[m];; });

		for(int i=0;i<this.egg.length;i++){
			if(this.egg[i]==true&&this.eggDistance[i]>=3){
				System.out.println("卵が孵った！");
				int m = (int)(this.monsterZukan.length*Math.random());
				System.out.println(this.monsterZukan[m]+"が産まれた！");

				for(int j=0;j<userMonster.length;j++){
					if(this.userMonster[j]==null){
						this.userMonster[j]=this.monsterZukan[m];
						break;
					}
				}
				this.egg[i]=false;
				this.eggDistance[i]=0.0;
			}
		}
	}

	void zooStation(int ball, int fruits){
		int b=(int)(Math.random()*3);//ball,fruits,eggがランダムに出る
		int f=(int)(Math.random()*2);
		int e=(int)(Math.random()*2);
		System.out.println("ボールを"+b+"個，"+"フルーツを"+f+"個"+"卵を"+e+"個Getした！");
		this.balls=this.balls+b;
		this.fruits=this.fruits+f;
		if(e>=1){//卵を1つ以上Getしたら
			//egg[]に10個以上卵がない場合は新しい卵データをセットする
			for(int i=0;i<this.eggDistance.length;i++){
				if(this.egg[i]==false){
					this.egg[i]=true;
					this.eggDistance[i]=0.0;
					break;
				}
			}
		}

	}

	void encountMonster(){
		int m = (int)(this.monsterZukan.length*Math.random());//monsterZukanからランダムにモンスターを出す
		System.out.println(this.monsterZukan[m]+"が現れた！");

		
		for(int i=0;i<3&&this.balls>0;i++){//捕まえる or 3回ボールを投げるまで繰り返す
			int r = (int)(6*Math.random());//0~5までの数字をランダムに返す
			if(this.fruits>0){
				System.out.println("フルーツを投げた！捕まえやすさが倍になる！");
				this.fruits--;
				r = r * 2;
			}
			System.out.println(this.monsterZukan[m]+"にボールを投げた");
			this.balls--;
			if(this.monsterRare[m]<=r){//monsterRare[m]の値がr以下の場合
				System.out.println(this.monsterZukan[m]+"を捕まえた！");
				for(int j=0;j<userMonster.length;j++){
					if(this.userMonster[j]==null){
						this.userMonster[j]=this.monsterZukan[m];
						break;
					}
				}
				break;//ボール投げ終了
			}else{
				System.out.println(this.monsterZukan[m]+"に逃げられた！");
			}
		}

	}

	public double getDistance() {
		return distance;
	}

	public int getBalls() {
		return balls;
	}

	public int getFruits() {
		return fruits;
	}

	public String[] getUserMonster() {
		return userMonster;
	}

	public void setMonsterZukan(String[] monsterZukan) {
		this.monsterZukan = monsterZukan;
	}

	public void setMonsterRare(double[] monsterRare) {
		this.monsterRare = monsterRare;
	}
}
