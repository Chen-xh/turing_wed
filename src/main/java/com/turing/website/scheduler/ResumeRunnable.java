package com.turing.website.scheduler;


/**
 * @author CHEN
 * @date 2020/3/11 15:33
 */
public class  ResumeRunnable implements Runnable {
     private static boolean resume=true;

    @Override
    public synchronized  void run() {
        resume= !resume;
    }

    public static boolean isResume() {
        return resume;
    }

    public synchronized static void setResume(boolean resume) {
        ResumeRunnable.resume = resume;
    }
}
