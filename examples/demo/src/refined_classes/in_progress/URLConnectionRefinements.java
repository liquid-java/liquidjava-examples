package refined_classes.in_progress;

import liquidjava.specification.*;
import java.net.URL;
import java.net.FileNameMap;
import java.net.ContentHandlerFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.security.Permission;
import java.util.Map;
import java.util.List;

@ExternalRefinementsFor("java.net.URLConnection")
@StateSet({ "unconnected", "connected" })
@Ghost("boolean doInput")
@Ghost("boolean doOutput")
@Ghost("boolean allowUserInteraction")
@Ghost("boolean useCaches")
@Ghost("int ifModifiedSince")
@Ghost("int connectTimeout")
@Ghost("int readTimeout")
public interface URLConnectionRefinements {

	// ======== Constructor ======== //
	
	@StateRefinement(to = "unconnected(this)")
	public abstract void URLConnection(@Refinement("url != null") URL url);

	// ======== Static Methods ======== //
	// Note: Static methods are represented as abstract instance methods in refinements
	
	public abstract FileNameMap getFileNameMap();
	
	public abstract void setFileNameMap(@Refinement("map != null") FileNameMap map);
	
	public abstract void setDefaultAllowUserInteraction(boolean defaultallowuserinteraction);
	
	public abstract boolean getDefaultAllowUserInteraction();
	
	public abstract boolean getDefaultUseCaches();
	
	public abstract void setDefaultUseCaches(boolean defaultusecaches);
	
	@Deprecated
	public abstract void setDefaultRequestProperty(@Refinement("key != null") String key, String value);
	
	@Deprecated
	public abstract String getDefaultRequestProperty(@Refinement("key != null") String key);
	
	public abstract void setContentHandlerFactory(@Refinement("fac != null") ContentHandlerFactory fac);
	
	public abstract String guessContentTypeFromName(String fname);
	
	public abstract String guessContentTypeFromStream(@Refinement("is != null") InputStream is) throws IOException;

	// ======== Connection Methods ======== //
	
	@StateRefinement(from = "unconnected(this)", to = "connected(this)")
	public abstract void connect() throws IOException;
	
	@StateRefinement(from = "unconnected(this)", to = "connectTimeout(this) == timeout")
	public abstract void setConnectTimeout(@Refinement("_ >= 0") int timeout);
	
	@Refinement("return == connectTimeout(this) || (return >= 0)")
	public abstract int getConnectTimeout();
	
	@StateRefinement(from = "unconnected(this)", to = "readTimeout(this) == timeout")
	public abstract void setReadTimeout(@Refinement("_ >= 0") int timeout);
	
	@Refinement("return == readTimeout(this) || (return >= 0)")
	public abstract int getReadTimeout();

	// ======== Configuration Methods (must be called before connect) ======== //
	
	@StateRefinement(from = "unconnected(this)", to = "doInput(this) == doinput")
	public abstract void setDoInput(boolean doinput);
	
	@Refinement("return == doInput(this)")
	public abstract boolean getDoInput();
	
	@StateRefinement(from = "unconnected(this)", to = "doOutput(this) == dooutput")
	public abstract void setDoOutput(boolean dooutput);
	
	@Refinement("return == doOutput(this)")
	public abstract boolean getDoOutput();
	
	@StateRefinement(from = "unconnected(this)", to = "allowUserInteraction(this) == allowuserinteraction")
	public abstract void setAllowUserInteraction(boolean allowuserinteraction);
	
	@Refinement("return == allowUserInteraction(this)")
	public abstract boolean getAllowUserInteraction();
	
	@StateRefinement(from = "unconnected(this)", to = "useCaches(this) == usecaches")
	public abstract void setUseCaches(boolean usecaches);
	
	@Refinement("return == useCaches(this)")
	public abstract boolean getUseCaches();
	
	@StateRefinement(from = "unconnected(this)", to = "ifModifiedSince(this) == ifmodifiedsince")
	public abstract void setIfModifiedSince(long ifmodifiedsince);
	
	@Refinement("return == ifModifiedSince(this)")
	public abstract long getIfModifiedSince();
	
	@StateRefinement(from = "unconnected(this)")
	public abstract void setRequestProperty(@Refinement("key != null") String key, String value);
	
	@StateRefinement(from = "unconnected(this)")
	public abstract void addRequestProperty(@Refinement("key != null") String key, String value);
	
	@StateRefinement(from = "unconnected(this)")
	public abstract String getRequestProperty(@Refinement("key != null") String key);
	
	@StateRefinement(from = "unconnected(this)")
	public abstract Map<String, List<String>> getRequestProperties();

	// ======== Content and Header Methods (require connection, may implicitly connect) ======== //
	
	@Refinement("return != null")
	public abstract URL getURL();
	
	// Methods that implicitly connect - allow both unconnected and connected states
	@Refinement("return >= -1")
	public abstract int getContentLength();
	
	@Refinement("return >= -1")
	public abstract long getContentLengthLong();
	
	public abstract String getContentType();
	
	public abstract String getContentEncoding();
	
	@Refinement("return >= 0")
	public abstract long getExpiration();
	
	@Refinement("return >= 0")
	public abstract long getDate();
	
	@Refinement("return >= 0")
	public abstract long getLastModified();
	
	public abstract String getHeaderField(@Refinement("name != null") String name);
	
	public abstract Map<String, List<String>> getHeaderFields();
	
	public abstract int getHeaderFieldInt(@Refinement("name != null") String name, int Default);
	
	public abstract long getHeaderFieldLong(@Refinement("name != null") String name, long Default);
	
	public abstract long getHeaderFieldDate(@Refinement("name != null") String name, long Default);
	
	public abstract String getHeaderFieldKey(@Refinement("_ >= 0") int n);
	
	public abstract String getHeaderField(@Refinement("_ >= 0") int n);
	
	public abstract Object getContent() throws IOException;
	
	public abstract Object getContent(@Refinement("classes != null") Class<?>[] classes) throws IOException;
	
	// getPermission may depend on connection state but can be called before connecting
	public abstract Permission getPermission() throws IOException;
	
	@StateRefinement(from = "connected(this)")
	public abstract InputStream getInputStream() throws IOException;
	
	@StateRefinement(from = "connected(this)")
	public abstract OutputStream getOutputStream() throws IOException;

	// ======== Other Methods ======== //
	
	public abstract String toString();
}

