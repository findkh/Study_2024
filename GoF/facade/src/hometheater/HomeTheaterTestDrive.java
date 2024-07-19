package hometheater;

public class HomeTheaterTestDrive {
	public static void main(String[] args) {
		Amplifier amp = new Amplifier("앰프");
		Tuner tuner = new Tuner("AM/FM 튜너", amp);
		StreamingPlayer player = new StreamingPlayer("스트리밍 플레이어", amp);
		CdPlayer cd = new CdPlayer("CD 플레이어", amp);
		Projector projector = new Projector("프로젝터", player);
		TheaterLights lights = new TheaterLights("조명");
		Screen screen = new Screen("스크린");
		PopcornPopper popper = new PopcornPopper("팝콘 기계");
 
		HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp,
				tuner,
				player,
				lights,
				projector,
				screen,
				popper);
 
		homeTheater.watchMovie("인사이드 아웃2");
		homeTheater.endMovie();
	}
}
