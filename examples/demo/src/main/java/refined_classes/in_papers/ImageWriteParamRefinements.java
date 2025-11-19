// Protocols in the wild
package refined_classes.in_papers;

import liquidjava.specification.*;
import java.util.Locale;
import java.awt.Dimension;

@ExternalRefinementsFor("javax.imageio.ImageWriteParam")
@Ghost("int tilingMode")
@Ghost("int progressiveMode")
@Ghost("int compressionMode")
@Ghost("boolean tilingSet")
@Ghost("boolean compressionTypeSet")
@RefinementAlias("TilingModeExplicit(ImageWriteParam p) { tilingMode(p) == 2 }")
@RefinementAlias("CompressionModeExplicit(ImageWriteParam p) { compressionMode(p) == 2 }")
public interface ImageWriteParamRefinements {

	// ======== Constants ======== //
	// MODE_DISABLED = 0, MODE_DEFAULT = 1, MODE_EXPLICIT = 2, MODE_COPY_FROM_METADATA = 3

	// ======== Constructors ======== //

	@StateRefinement(to = "tilingMode(this) == 3 && progressiveMode(this) == 3 && compressionMode(this) == 3 && tilingSet(this) == false && compressionTypeSet(this) == false")
	public abstract void ImageWriteParam();

	@StateRefinement(to = "tilingMode(this) == 3 && progressiveMode(this) == 3 && compressionMode(this) == 3 && tilingSet(this) == false && compressionTypeSet(this) == false")
	public abstract void ImageWriteParam(Locale locale);

	// ======== Observers ======== //

	public abstract Locale getLocale();

	public abstract boolean canWriteTiles();

	public abstract boolean canOffsetTiles();

	@Refinement("return == tilingMode(this)")
	public abstract int getTilingMode();

	public abstract Dimension[] getPreferredTileSizes();

	@StateRefinement(from = "TilingModeExplicit(this) && tilingSet(this)")
	@Refinement("return > 0")
	public abstract int getTileWidth();

	@StateRefinement(from = "TilingModeExplicit(this) && tilingSet(this)")
	@Refinement("return > 0")
	public abstract int getTileHeight();

	@StateRefinement(from = "TilingModeExplicit(this) && tilingSet(this)")
	@Refinement("return >= 0")
	public abstract int getTileGridXOffset();

	@StateRefinement(from = "TilingModeExplicit(this) && tilingSet(this)")
	@Refinement("return >= 0")
	public abstract int getTileGridYOffset();

	public abstract boolean canWriteProgressive();

	@Refinement("return == progressiveMode(this)")
	public abstract int getProgressiveMode();

	public abstract boolean canWriteCompressed();

	@Refinement("return == compressionMode(this)")
	public abstract int getCompressionMode();

	public abstract String[] getCompressionTypes();

	@StateRefinement(from = "CompressionModeExplicit(this)")
	public abstract String getCompressionType();

	@StateRefinement(from = "CompressionModeExplicit(this) && compressionTypeSet(this)")
	@Refinement("return != null")
	public abstract String getLocalizedCompressionTypeName();

	@StateRefinement(from = "CompressionModeExplicit(this)")
	public abstract boolean isCompressionLossless();

	@StateRefinement(from = "CompressionModeExplicit(this) && compressionTypeSet(this)")
	public abstract void setCompressionQuality(@Refinement("0.0 <= _ && _ <= 1.0") float quality);

	@StateRefinement(from = "CompressionModeExplicit(this) && compressionTypeSet(this)")
	@Refinement("0.0 <= return && return <= 1.0")
	public abstract float getCompressionQuality();

	@StateRefinement(from = "CompressionModeExplicit(this) && compressionTypeSet(this)")
	@Refinement("return >= -1.0")
	public abstract float getBitRate(@Refinement("0.0 <= _ && _ <= 1.0") float quality);

	@StateRefinement(from = "CompressionModeExplicit(this) && compressionTypeSet(this)")
	public abstract String[] getCompressionQualityDescriptions();

	@StateRefinement(from = "CompressionModeExplicit(this) && compressionTypeSet(this)")
	public abstract float[] getCompressionQualityValues();
	
	// ======== Mutators ======== //

	@StateRefinement(to = "tilingMode(this) == mode")
	public abstract void setTilingMode(@Refinement("0 <= _ && _ <= 3") int mode);

	@StateRefinement(from = "TilingModeExplicit(this)", to = "tilingSet(this)")
	public abstract void setTiling(@Refinement("_ > 0") int tileWidth, @Refinement("_ > 0") int tileHeight, @Refinement("_ >= 0") int tileGridXOffset, @Refinement("_ >= 0") int tileGridYOffset);

	@StateRefinement(from = "TilingModeExplicit(this)", to = "tilingSet(this) == false")
	public abstract void unsetTiling();

	@StateRefinement(to = "progressiveMode(this) == mode")
	public abstract void setProgressiveMode(@Refinement("0 <= _ && _ <= 3 && _ != 2") int mode);

	@StateRefinement(to = "compressionMode(this) == mode")
	public abstract void setCompressionMode(@Refinement("0 <= _ && _ <= 3") int mode);

	@StateRefinement(from = "CompressionModeExplicit(this)", to = "compressionTypeSet(this)")
	public abstract void setCompressionType(String compressionType);

	@StateRefinement(from = "CompressionModeExplicit(this)", to = "compressionTypeSet(this) == false")
	public abstract void unsetCompression();

	public interface IIOParamRefinements {
	// defined in parent class IIOParam
	// public abstract void setSourceRegion(Rectangle sourceRegion);

	// public abstract Rectangle getSourceRegion();

	// public abstract void setSourceSubsampling(@Refinement("_ > 0") int sourceXSubsampling, @Refinement("_ > 0") int sourceYSubsampling, @Refinement("_ >= 0") int subsamplingXOffset, @Refinement("_ >= 0") int subsamplingYOffset);

	// @Refinement("return > 0")
	// public abstract int getSourceXSubsampling();

	// @Refinement("return > 0")
	// public abstract int get SourceYSubsampling();

	// @Refinement("return >= 0")
	// public abstract int getSubsamplingXOffset();

	// @Refinement("return >= 0")
	// public abstract int getSubsamplingYOffset();

	// public abstract void setSourceBands(int[] sourceBands);

	// public abstract int[] getSourceBands();

	// public abstract void setDestinationType(ImageTypeSpecifier destinationType);

	// public abstract ImageTypeSpecifier getDestinationType();

	// public abstract void setDestinationOffset(Point destinationOffset);

	// public abstract Point getDestinationOffset();

	// public abstract void setController(IIOParamController controller);

	// public abstract IIOParamController getController();

	// public abstract IIOParamController getDefaultController();

	// public abstract boolean hasController();

	// public abstract boolean activateController();
}
}
