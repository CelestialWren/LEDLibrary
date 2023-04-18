package frc;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.Animations.Animation;

public class SetAnimationCommand extends CommandBase {
    final Animation animation;
    boolean endCommand;
    LedStrip led;
    Timer time;
    double timeToRunFor = -1;
    /**A command that runs the animation passed in on the light  */
    public SetAnimationCommand(LedStrip led, Animation animation){
        addRequirements(led);
        this.led = led;
        this.animation = animation;
        endCommand = false;
        time = new Timer();
    }
    public SetAnimationCommand(LedStrip led, Animation animation, double timeToRunFor){
        addRequirements(led);
        this.led = led;
        this.animation = animation;
        this.timeToRunFor = timeToRunFor;
        endCommand = false;
        time = new Timer();
        time.start();
    }
    @Override
    public void initialize(){
        led.setAnimation(animation);
    }

    @Override
    public void end(boolean isInterupted){
        led.stopAnimation();
        time.reset();
    }

    @Override
    public boolean isFinished(){
        if(time.get() >= timeToRunFor){
            endCommand = true;
        }
        return endCommand;
    }
}
