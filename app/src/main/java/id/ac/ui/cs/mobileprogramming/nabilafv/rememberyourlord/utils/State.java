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

    public static class Loading<T> extends State{};

    public static class Success<T> extends State{
        public T data;
        public Success(T data){
            this.data = data;
        };
    };

    public static class Failed<T> extends State {
        public Throwable throwable;
        public Failed(Throwable throwable) {
            this.throwable = throwable;
        }
    }

    public static class Initialize<T> extends State{};
}