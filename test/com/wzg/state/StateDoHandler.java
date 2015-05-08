package com.wzg.state;

import org.apache.mina.statemachine.StateMachine;
import org.apache.mina.statemachine.StateMachineFactory;
import org.apache.mina.statemachine.StateMachineProxyBuilder;
import org.apache.mina.statemachine.annotation.State;
import org.apache.mina.statemachine.annotation.Transition;
import org.apache.mina.statemachine.annotation.Transitions;

public class StateDoHandler {
	@State
	public static final String EMPTY = "Empty";
	@State
	public static final String LOADED = "Loaded";
	@State
	public static final String PLAYING = "Playing";
	@State
	public static final String PAUSED = "Paused";

	@Transition(on = "load", in = EMPTY, next = LOADED)
	public void load(String nameOfTape) {
		System.out.println("Tape '" + nameOfTape + "' loaded");
	}

	@Transitions({ @Transition(on = "play", in = LOADED, next = PLAYING), @Transition(on = "play", in = PAUSED, next = PLAYING) })
	public void play() {
		System.out.println("Playing tape");
	}

	@Transition(on = "pause", in = PLAYING, next = PAUSED)
	public void pause() {
		System.out.println("Tape paused");
	}

	@Transition(on = "stop", in = PLAYING, next = LOADED)
	public void stop() {
		System.out.println("Tape stopped");
	}

	@Transition(on = "eject", in = LOADED, next = EMPTY)
	public void eject() {
		System.out.println("Tape ejected");
	}

	public static void main(String[] args) {
		StateDoHandler handler = new StateDoHandler();// 自定义的状态和方法
		StateMachine sm = StateMachineFactory.getInstance(Transition.class).create(StateDoHandler.EMPTY, handler);// 用工厂方法基于Transition注解创建一个状态机，初始状态为Empty，处理方法基于自定义的类
		StateDo deck = new StateMachineProxyBuilder().create(StateDo.class, sm); // 创建一个状态机代理对象，代理上一步产生的状态机
		deck.load("The Knife - Silent Shout");
		deck.play();
		deck.pause();
		deck.play();
		deck.stop();
		// deck.eject();}
	}

}
