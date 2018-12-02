package ta.thread;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestThreadContext implements ThreadContext {

    @Override
    public Map<String, Serializable> getContext() {
        return new HashMap<>();
    }
}
