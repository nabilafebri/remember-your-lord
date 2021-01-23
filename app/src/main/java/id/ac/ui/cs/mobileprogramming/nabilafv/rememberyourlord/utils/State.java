package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils;

public class State<T> {
    public State<T> loading() {
        return new Loading<T>();
    }

    public State<T> success(T data) {
        return new Success<T>(data);
    }

    public State<T> failed(Throwable throwable) {
        return new Failed<T>(throwable);
    }

    public State<T> init() {
        return new Initialize<T>();
    }

    class Loading<T> extends State{};

    class Success<T> extends State{
        public T data;
        Success(T data){
            this.data = data;
        };
    };

    class Failed<T> extends State {
        public Throwable throwable;
        Failed(Throwable throwable) {
            this.throwable = throwable;
        }
    }

    class Initialize<T> extends State{};
}