package com.module.DesignMode.command;

import java.util.Arrays;

public class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommands(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n-----------Remote Control-----------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuilder.append("[slot").append(i).append("]")
                    .append(onCommands[i].getClass().getName())
                    .append("    ").append(offCommands[i].getClass().getName()).append("\n");
        }
        return stringBuilder.toString();
    }
}
