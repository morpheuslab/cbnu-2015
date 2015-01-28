package com.example.chap10.examples;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.example.chap10.R;

public class NumPang extends Activity {
	GameView gameView;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameView = new GameView(this);
		setContentView(gameView);
	}

	public void onPause() {
		super.onPause();
		gameView.onPause();
	}

	public void onResume() {
		super.onResume();
		gameView.onResume();
	}

	class GameView extends View {
		Activity parentActivity;					// 부모 액티비티
		int maxNum = 6;					// 숫자의 개수. 최대 9
		int width = 7;						// 게임판의 가로 크기. 최대 29
		int height = 8;					// 게임판의 세로 크기. 최대 39
		int tileWidth, tileHeight;		// 타일 하나의 크기
		int tileMargin;					// 타일간의 간격
		int textSize;						// 숫자 텍스트의 크기
		int edgeThick;						// 경계선의 두께
		Rect timeRect;						// 시간을 출력할 영역
		Point scorePt;						// 점수를 출력할 좌표
		Point boardPt;						// 게임판 좌상단 좌표
		int removeSpeed = 20;				// 타일 제거 속도
		int removeDelay = 200;				// 타일 제거후 대기 시간
		int makeDelay = 200;				// 새 타일 만든 후 대기 시간
		int gameTime = 60;					// 게임 지속 시간
		int ellapse;						// 게임 경과 시간
		int score;							// 현재 점수
		int combo;							// 콤보 회수
		boolean starting;					// 게임 초기화중. 점수 계산하지 않음
		int status;						// 현재 상태
		final static int PLAY = 0;			// 게임중. 입력 가능
		final static int REMOVE = 1;		// 제거중
		final static int MAKE = 2;			// 새로 만드는 중
		int nowX = -1, nowY = -1;			// 최초 터치한 타일 좌표
		float scale = 1.0f;				// 축소 진행값 
		Paint tileEdgePaint, tilePaint, textPaint;
		Paint timeEdgePaint, timeGaugePaint, scorePaint;
		// 타일의 배경 색과 글자색
		int[] backgroundColors = {0, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, 
				Color.CYAN, Color.MAGENTA, Color.BLACK, Color.GRAY, Color.WHITE };
		int[] textColors = {0, Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, 
				Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK };
		// 게임판 배열
		int[][] board = new int[30][40];
		// 삭제할 타일 목록 배열
		boolean[][] deadTile = new boolean[30][40];
		// 난수, 진동, 사운드 관련 객체
		Random rnd = new Random();
		Vibrator vib;
		SoundPool pool;
		int removeSound;
		
		public GameView(Context context) {
			super(context);
			parentActivity = (Activity)context;

			// 진동 및 사운드 재생 준비
			vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
			removeSound = pool.load(context, R.raw.remove, 1);

			// 타일 하나의 크기를 계산해 둔다.
			DisplayMetrics dm = getResources().getDisplayMetrics();
			tileWidth = tileHeight = dm.widthPixels / width;
			tileMargin = (int)(tileWidth * 0.08f);
			edgeThick = (int)(tileWidth * 0.05f);
			textSize = tileHeight / 2;
			
			// 시간 및 점수 출력 영역
			timeRect = new Rect(0, 0, dm.widthPixels * 7/10, dm.heightPixels / 10);
			timeRect.inset(timeRect.width() * 5/100, timeRect.height() * 20/100);
			scorePt = new Point(dm.widthPixels * 95/100, timeRect.bottom);
			boardPt = new Point((dm.widthPixels - tileWidth * width) / 2,
					dm.heightPixels/10);
			
			// Paint 객체 생성
			tileEdgePaint = new Paint();
			tileEdgePaint.setStyle(Paint.Style.STROKE);
			tileEdgePaint.setColor(Color.BLACK);
			tilePaint = new Paint();
			tilePaint.setStyle(Paint.Style.FILL);
			textPaint = new Paint();
			textPaint.setTextAlign(Paint.Align.CENTER);
			timeEdgePaint = new Paint();
			timeEdgePaint.setStyle(Paint.Style.STROKE);
			timeEdgePaint.setColor(Color.BLUE);
			timeEdgePaint.setStrokeWidth(timeRect.height()/10);
			timeGaugePaint = new Paint();
			timeGaugePaint.setColor(0xff008000);
			scorePaint = new Paint();
			scorePaint.setTextAlign(Paint.Align.RIGHT);
			scorePaint.setTextSize(timeRect.height());

			startGame();
		}
		
		// 게임 일시 정지 및 재개 처리
		boolean hasTimer;
		void onPause() {
			hasTimer = handler.hasMessages(100); 
			if (hasTimer) {
				handler.removeMessages(100);
			}
		}
		
		void onResume() {
			if (hasTimer) {
				handler.sendEmptyMessage(100);
			}
		}
		
		// 새 게임을 시작한다.
		void startGame() {
			// 게임판을 난수로 채워 넣는다.
			for (int x=0;x<width;x++) {
				for (int y=0;y<height;y++) {
					board[x][y] = rnd.nextInt(maxNum) + 1;
				}
			}

			// 점수와 시간을 초기화한다.
			score = 0;
			ellapse = 0;

			// 연속된 타일이 있으면 제거한다. 없으면 바로 시작한다.
			if (checkContinuousTile()) {
				starting = true;
				status = REMOVE;
				scale = 1.0f;
				handler.sendEmptyMessageDelayed(0, makeDelay);
			} else {
				starting = false;
				status = PLAY;
				handler.sendEmptyMessage(100);
			}
		}
		
		public void onDraw(Canvas canvas) {
			canvas.drawColor(Color.LTGRAY);

			// 남은 시간과 점수를 그린다. 시작중일 때는 꽉 찬 형태로 그림
			Rect rect = new Rect(timeRect);
			if (starting == false) {
				rect.right = Math.max(timeRect.right - ellapse * 
						timeRect.width() / gameTime, timeRect.left);
			}
			canvas.drawRect(rect, timeGaugePaint);
			canvas.drawRect(timeRect, timeEdgePaint);

			// 점수를 출력한다.
			canvas.drawText("" + score, scorePt.x, scorePt.y, scorePaint);
			
			// 게임판을 그린다.
			for (int x=0;x<width;x++) {
				for (int y=0;y<height;y++) {
					// 일시적으로 제거된 빈칸은 그리지 않는다.
					if (board[x][y] == -1) continue;
					
					// 타일의 영역에 마진을 뺀다.
					int tx = boardPt.x + x * tileWidth;
					int ty = boardPt.y + y * tileHeight;
					Rect rt = new Rect(tx, ty, tx + tileWidth, ty + tileHeight);
					rt.inset(tileMargin, tileMargin);
					
					// 일반 타일의 그리기 속성을 설정한다.
					textPaint.setTextSize(textSize);
					tileEdgePaint.setStrokeWidth(edgeThick);
					float texty = textSize / 2;

					// 삭제된 타일은 점점 작아진다.
					if (status == REMOVE && deadTile[x][y]) {
						rt.inset((int)(rt.width() / 2 * (1 - scale)), 
								(int)(rt.height() / 2 * (1 - scale)));
						textPaint.setTextSize(textSize * scale);
						texty = (textSize * scale) / 2;
						tileEdgePaint.setStrokeWidth(edgeThick * scale);
					}

					// 타일 및 숫자 출력
					tilePaint.setColor(backgroundColors[board[x][y]]);
					canvas.drawRect(rt, tilePaint);
					canvas.drawRect(rt, tileEdgePaint);
					textPaint.setColor(textColors[board[x][y]]);
					canvas.drawText("" + board[x][y], tx + tileWidth/2, 
							ty + tileHeight/2 + texty, textPaint);
				}
			}
		}
		
		public boolean onTouchEvent(MotionEvent event) {
			// 애니메이션 중에는 터치 입력을 무시한다.
			if (status != PLAY) {
				return true;
			}
			// 최초 터치시 터치한 좌표를 기억해 둔다.
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				nowX = getBoardX(event.getX());
				nowY = getBoardY(event.getY());
				return true;
			}

			// 이동시 이동한 방향을 판별한다.
			if (event.getAction() == MotionEvent.ACTION_MOVE) {
				// 한번 이동했으면 다음 터치시까지 무시한다.
				if (nowX == -1) return true;
				
				int nx = getBoardX(event.getX());
				int ny = getBoardY(event.getY());
				
				// 교체 가능하면 교체한다.
				if (checkSwappable(nowX, nowY, nx, ny)) {
					swapTile(nowX, nowY, nx, ny);
					nowX = nowY = -1;
					invalidate();
					combo = 1;
					status = REMOVE;
					scale = 1.0f;
					handler.sendEmptyMessage(0);
				}
			}
			return true;
		}

		// 화면상의 좌표를 게임판상의 좌표로 바꾼다.
		// 게임판을 벗어나면 안쪽으로 넣어 준다. 
		int getBoardX(float scrx) {
			int x = (int)((scrx - boardPt.x) / tileWidth);
			if (x < 0) x = 0;
			if (x > width - 1) x = width - 1;
			return x;
		}
		
		int getBoardY(float scry) {
			int y = (int)((scry - boardPt.y) / tileHeight);
			if (y < 0) y = 0;
			if (y > height - 1) y = height - 1;
			return y;
		}
		
		// 교체 가능한지 점검한다.
		boolean checkSwappable(int x1, int y1, int x2, int y2) {
			// 같은 위치이면 교체 불가하다.
			if (x1 == x2 && y1 == y2) return false;
			
			// 대각선 이동은 불가능하다.
			if (x1 != x2 && y1 != y2) return false;
			
			// 두칸 이상 이동은 불가하다.
			if (Math.abs(x1 - x2) > 1 || Math.abs(y1 - y2) > 1) return false;
			
			// 타일을 바꿔 보고 연속성 여부를 판별한다.
			swapTile(x1, y1, x2, y2);
			boolean swappable = checkContinuousTile(); 
			swapTile(x1, y1, x2, y2);
			return swappable;
		}

		// 배열의 두 타일을 교환한다.
		void swapTile(int x1, int y1, int x2, int y2) {
			int swaptemp = board[x1][y1];
			board[x1][y1] = board[x2][y2];
			board[x2][y2] = swaptemp;
		}
		
		// 3회 이상 같은 숫자가 있는지 점검하고 삭제 대상 타일에 마킹한다.
		boolean checkContinuousTile() {
			boolean found = false;
			// 모든 칸을 리셋한다.
			for (int x=0;x<width;x++) {
				for (int y=0;y<height;y++) {
					deadTile[x][y] = false;
				}
			}

			// 전체 순회하며 3연속을 찾는다.
			for (int x=0;x<width;x++) {
				for (int y=0;y<height;y++) {
					int num = board[x][y];
					
					// 수평 3개가 같으면 제거 대상이다. 첫열, 끝열은 검사하지 않는다.
					if (x > 0 && x < width - 1) {
						if (board[x-1][y] == num && board[x+1][y] == num) {
							deadTile[x-1][y] = true;
							deadTile[x][y] = true;
							deadTile[x+1][y] = true;
							found = true;
						}
					}
					
					// 수직 3개가 같으면 제거 대상이다. 첫행, 끝행은 검사하지 않는다.
					if (y > 0 && x < height - 1) {
						if (board[x][y-1] == num && board[x][y+1] == num) {
							deadTile[x][y-1] = true;
							deadTile[x][y] = true;
							deadTile[x][y+1] = true;
							found = true;
						}
					}
				}
			}
			
			return found;
		}
		
		// 연속된 타일을 제거한다.
		void removeTiles() {
			pool.play(removeSound, 1, 1, 0, 0, 1);
			vib.vibrate(100);
			int count = 0;
			// 제거할 타일을 기록한다.
			for (int x=0;x<width;x++) {
				for (int y=0;y<height;y++) {
					if (deadTile[x][y]) {
						board[x][y] = 0;
						count++;
					}
				}
			}

			// 시작 중에는 점수를 증가시키지 않는다.
			if (starting == false) {
				score += (count * combo);
			}
			
			// 밑에서부터 순서대로 제거해온다.
			boolean found;
			for (int y = height - 1;y >= 0;y--) {
				found = false;
				for (int x=0;x<width;x++) {
					if (board[x][y] == 0) {
						found = true;
						for (int ty = y;ty > 0;ty--) {
							board[x][ty] = board[x][ty-1];
						}
						// 새로 만들어야 할 타일에 -1을 기록한다.
						board[x][0] = -1;
					}
				}
				
				// 제거했으면 그 줄은 다시 검사한다.
				if (found) y++;
			}
		}
		
		// 새로 생긴 빈칸에 새 타일을 만든다.
		void makeNewTile() {
			for (int x=0;x<width;x++) {
				for (int y=0;y<height;y++) {
					if (board[x][y] == -1) {
						board[x][y] = rnd.nextInt(maxNum) + 1;
					}
				}
			}
		}

		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				// 제거할 타일을 점점 작게 축소한다.
				case 0:
					scale -= 0.1f;
					if (scale > 0.1f) {
						sendEmptyMessageDelayed(0, removeSpeed);
						invalidate();
					} else {
						status = MAKE;
						sendEmptyMessage(1);
					}
					break;
				// 타일을 제거한다.
				case 1:
					removeTiles();
					invalidate();
					sendEmptyMessageDelayed(2, removeDelay);
					break;
				// 새 타일을 만든다.
				case 2:
					makeNewTile();
					invalidate();
					sendEmptyMessageDelayed(3, makeDelay);
					break;
				// 다시 점검한다.
				case 3:
					if (checkContinuousTile()) {
						status = REMOVE;
						scale = 1.0f;
						combo++;
						sendEmptyMessage(0);
					} else {
						status = PLAY;
						if (starting) {
							starting = false;
							// 규칙에 맞지 않은 벽돌 완전 제거 후 타이머를 시작한다. 
							handler.sendEmptyMessage(100);
						}
					}
					invalidate();
					break;
				case 100:
					if (ellapse > gameTime) {
						new AlertDialog.Builder(parentActivity)
						.setTitle("Game Over")
						.setMessage("다시 시작할까요?")
						.setPositiveButton("예", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								startGame();
							}
						})
						.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								parentActivity.finish();
							}
						})
						.show();
					} else {
						ellapse++;
						invalidate();
						handler.sendEmptyMessageDelayed(100, 1000);
					}
					break;
				}
			}
		};
	}
}
