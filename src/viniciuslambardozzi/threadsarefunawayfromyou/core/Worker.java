package viniciuslambardozzi.threadsarefunawayfromyou.core;

public abstract class Worker implements Runnable
{
    IWorkerManager manager;

    public Worker(IWorkerManager manager)
    {
        this.manager = manager;
    }

    @Override
    public void run()
    {
        onWorkerStart();

        work();

        onWorkerFinish();
    }

    protected abstract void onWorkerStart();
    protected abstract void work();
    protected abstract void onWorkerFinish();
}
