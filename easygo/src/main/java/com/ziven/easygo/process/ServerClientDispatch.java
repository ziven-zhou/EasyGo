package com.ziven.easygo.process;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.ziven.easygo.process.client.ClientHandler;
import com.ziven.easygo.process.server.ServerHandler;
import com.ziven.easygo.util.Nulls;

import java.util.Objects;

/**
 * @author Ziven
 */
@Keep
class ServerClientDispatch {

    private ProcessHandler handler;

    void setProcessHandler(@Nullable ProcessHandler handler) {
        this.handler = handler;
    }

    ProcessSender getProcessSender() {
        return Objects
                .requireNonNull(handler, "ProcessHandler not init.")
                .getProcessSender();
    }

    Nulls<ClientHandler> clientHandler() {
        return handler instanceof ClientHandler
                ? Nulls.of((ClientHandler) handler)
                : Nulls.of();
    }

    Nulls<ServerHandler> serverHandler() {
        return handler instanceof ServerHandler
                ? Nulls.of((ServerHandler) handler)
                : Nulls.of();
    }

    void setProcessReceiver(@Nullable ProcessReceiver receiver) {
        if(handler != null) {
            handler.setProcessReceiver(receiver);
        }
    }
}
