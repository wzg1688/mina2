package com.wzg.state;

public interface StateDo {
	void load(String nameOfTape);

	void eject();

	void play();

	void pause();

	void stop();
}
