package viniciuslambardozzi.threadsarefunawayfromyou.core;

import viniciuslambardozzi.threadsarefunawayfromyou.core.util.Timer;

public abstract class Worker implements Runnable
{
    public IWorkerManager manager;
    public Timer timer;

    public Worker(IWorkerManager manager)
    {
        this.manager = manager;
        this.timer = new Timer();
    }

    @Override
    public void run()
    {
        onWorkerStart();
        timer.start();
        work();
        timer.stop();
        onWorkerFinish();
    }

    protected abstract void onWorkerStart();
    protected abstract void work();
    protected abstract void onWorkerFinish();
}
