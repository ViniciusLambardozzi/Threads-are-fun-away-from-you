package viniciuslambardozzi.threadsarefunawayfromyou.core;

public interface IWorkerManager
{
    void registerWorker(Thread thread);
    void joinWorkers();
    void manageWorkerResult(Worker worker);
}
