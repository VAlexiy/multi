package ta.thread;

import java.io.Serializable;
import java.util.Map;

public interface ThreadContext {

    Map<String, Serializable> getContext();
}
