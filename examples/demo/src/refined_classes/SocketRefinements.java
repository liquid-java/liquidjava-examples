package refined_classes;

import liquidjava.specification.*;
import java.net.SocketAddress;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.SocketImpl;
import java.net.SocketImplFactory;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketOption;
import java.util.Set;
import java.net.Socket;

@ExternalRefinementsFor("java.net.Socket")
@StateSet({ "unconnected", "bound", "connected", "closed" })
// IsBound(s) signifies whether the socket is bound or bound and connected
@RefinementAlias("IsBound(Socket s) { connected(s) || bound(s) }")
// WasBound(s) signifies whether the socket was bound before it was closed
@RefinementAlias("WasBound(Socket s) { closed(s) && !unconnected(old(s)) }")
// Connected(s) signifies whether the socket has ever been connected
@RefinementAlias("Connected(Socket s) { connected(s) || connected(old(s)) }")
@RefinementAlias("IsPort(int p) { 0 <= p && p <= 65535 }")
@Ghost("boolean shutdownInput")
@Ghost("boolean shutdownOutput")
@Ghost("boolean oobInline")
@Ghost("boolean tcpNoDelay")
@Ghost("int soLinger")
@Ghost("boolean keepAlive")
@Ghost("boolean reuseAddress")
@Ghost("int trafficClass")
@Ghost("int sendBufferSize")
@Ghost("int receiveBufferSize")
@Ghost("int soTimeout")
public interface SocketRefinements {

	// ======== Constructors ======== //
	@StateRefinement(to = "unconnected(this)")
	public abstract void Socket();

	@StateRefinement(to = "unconnected(this)")
	public abstract void Socket(@Refinement("proxy != null") Proxy proxy);

	@StateRefinement(to = "unconnected(this)")
	public abstract void Socket(@Refinement("impl != null") SocketImpl impl) throws SocketException;

	@StateRefinement(to = "connected(this)")
	public abstract void Socket(String host, @Refinement("IsPort(_)") int port)
			throws UnknownHostException, IOException;

	@StateRefinement(to = "connected(this)")
	public abstract void Socket(@Refinement("addr != null") InetAddress addr, @Refinement("IsPort(_)") int port) throws IOException;

	@StateRefinement(to = "connected(this)")
	public abstract void Socket(String host, @Refinement("IsPort(_)") int port, InetAddress localAddr,
			@Refinement("IsPort(_)") int localPort)
			throws UnknownHostException, IOException;

	@StateRefinement(to = "connected(this)")
	public abstract void Socket(@Refinement("addr != null") InetAddress addr, @Refinement("IsPort(_)") int port, @Refinement("localAddr != null") InetAddress localAddr,
			@Refinement("IsPort(_)") int localPort) throws IOException;

	@Deprecated
	@StateRefinement(to = "connected(this)")
	public abstract void Socket(@Refinement("addr != null") InetAddress addr, @Refinement("IsPort(_)") int port, boolean stream)
			throws IOException;

	@Deprecated
	@StateRefinement(to = "connected(this)")
	public abstract void Socket(String host, @Refinement("IsPort(_)") int port, boolean stream)
			throws UnknownHostException, IOException;

	// ======== Observers ======== //

	@StateRefinement(from = "connected(this)")
	public abstract InetAddress getInetAddress();

	@StateRefinement(from = "IsBound(this)")
	@Refinement("IsPort(return)")
	public abstract InetAddress getLocalAddress();

	@StateRefinement(from = "IsBound(this) || WasBound(this)")
	@Refinement("IsPort(return)")
	public abstract int getPort();

	@StateRefinement(from = "IsBound(this) || WasBound(this)")
	public abstract int getLocalPort();

	@StateRefinement(from = "connected(this)")
	public abstract SocketAddress getRemoteSocketAddress();

	@StateRefinement(from = "IsBound(this) || WasBound(this)")
	public abstract SocketAddress getLocalSocketAddress();

	public abstract SocketChannel getChannel();

	@StateRefinement(from = "connected(this) && !shutdownInput(this)")
	public abstract InputStream getInputStream() throws IOException;

	@StateRefinement(from = "connected(this)")
	public abstract OutputStream getOutputStream() throws IOException;

	@StateRefinement(from = "!closed(this)")
	@Refinement("return == tcpNoDelay(this)")
	public abstract boolean getTcpNoDelay();

	@StateRefinement(from = "!closed(this)")
	@Refinement("return == soLinger(this) || (return >= 0)")
	public abstract int getSoLinger();

	@StateRefinement(from = "!closed(this)")
	@Refinement("return == oobInline(this)")
	public abstract boolean getOOBInline();

	@Refinement("return == soTimeout(this) || (return >= 0)")
	// TODO ? check if when failing to determine whether its equal to the ghost it
	// checks if its within the bounds
	public abstract int getSoTimeout();

	@Refinement("return == sendBufferSize(this) || (return >= 0)")
	// TODO ? check if when failing to determine whether its equal to the ghost it
	// checks if its within the bounds
	public abstract int getSendBufferSize();

	@Refinement("return == receiveBufferSize(this) || (return >= 0)")
	// TODO ? check if when failing to determine whether its equal to the ghost it
	// checks if its within the bounds
	public abstract int getReceiveBufferSize();

	@StateRefinement(from = "!closed(this)")
	@Refinement("return == keepAlive(this)")
	public abstract boolean getKeepAlive();

	@Refinement("return == trafficClass(this) || (0 <= return && return <= 255)")
	// TODO ? check if when failing to determine whether its equal to the ghost it
	// checks if its within the bounds
	public abstract int getTrafficClass();

	@StateRefinement(from = "!closed(this)")
	@Refinement("return == reuseAddress(this)")
	public abstract boolean getReuseAddress();

	@StateRefinement(from = "!closed(this)")
	public abstract <T> T getOption(@Refinement("name != null") SocketOption<T> name);

	public abstract Set<SocketOption<?>> supportedOptions();

	// ======== Mutators ======== //

	@StateRefinement(from = "bound(this)", to = "connected(this)")
	public abstract void connect(@Refinement("add != null") SocketAddress add);

	@StateRefinement(from = "bound(this)", to = "connected(this)")
	public abstract void connect(@Refinement("add != null") SocketAddress add, @Refinement("_ >= 0") int timeout);

	@StateRefinement(from = "unconnected(this)", to = "bound(this)")
	public abstract void bind(SocketAddress add);

	@StateRefinement(from = "!closed(this)", to = "tcpNoDelay(this) == on")
	public abstract void setTcpNoDelay(boolean on);

	@StateRefinement(from = "!closed(this)", to = "soLinger(this) == linger")
	public abstract void setSoLinger(boolean on, int linger);

	@StateRefinement(from = "connected(this)") /* Unspecified */
	public abstract void sendUrgentData(int data);

	@StateRefinement(from = "!closed(this)", to = "oobInline(this) == on")
	public abstract void setOOBInline(boolean on);

	@StateRefinement(from = "!closed(this)", to = "soTimeout(this) == timeout")
	public abstract void setSoTimeout(@Refinement("_ >= 0") int timeout);

	@StateRefinement(from = "!closed(this)", to = "sendBufferSize(this) == size")

	public abstract void setSendBufferSize(@Refinement("_ >= 0") int size);

	@StateRefinement(from = "!closed(this)", to = "receiveBufferSize(this) == size")
	public abstract void setReceiveBufferSize(@Refinement("_ >= 0") int size);

	@StateRefinement(from = "!closed(this)", to = "keepAlive(this) == on")
	public abstract void setKeepAlive(boolean on);

	@StateRefinement(from = "!closed(this)", to = "trafficClass(this) == tc")
	public abstract void setTrafficClass(@Refinement("0 <= _ && _ <= 255") int tc);

	@StateRefinement(from = "!closed(this)", to = "reuseAddress(this) == on")
	public abstract void setReuseAddress(boolean on);

	@StateRefinement(from = "!closed(this)")
	public abstract void close();

	@StateRefinement(from = "connected(this)", to = "shutdownInput(this)")
	public abstract void shutdownInput();

	@StateRefinement(from = "connected(this)", to = "shutdownOutput(this)")
	public abstract void shutdownOutput();

	@Refinement("return == Connected(this)")
	public abstract boolean isConnected();

	@Refinement("return == IsBound(this)")
	public abstract boolean isBound();

	@Refinement("return == closed(this)")
	public abstract boolean isClosed();

	@Refinement("return == shutdownInput(this) ")
	public abstract boolean isInputShutdown();

	@Refinement("return == shutdownOutput(this) ")
	public abstract boolean isOutputShutdown();

	@Deprecated
	public abstract void setSocketImplFactory(@Refinement("factory != null") SocketImplFactory factory);

	@StateRefinement(from = "!closed(this)")
	public abstract <T> Socket setOption(@Refinement("name != null") SocketOption<T> name, T value);
}
