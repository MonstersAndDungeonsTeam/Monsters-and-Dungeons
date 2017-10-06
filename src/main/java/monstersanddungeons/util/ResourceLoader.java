package monstersanddungeons.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;

public class ResourceLoader extends FileResourcePack {


	public ResourceLoader(File resourcePackFileIn) {
		super(resourcePackFileIn);
		
	
	}

	@Override
	public Set<String> getResourceDomains() {
		// TODO Auto-generated method stub

		return ImmutableSet.of(Reference.MOD_ID_GenBlocks);
	}

	@Override
	public <T extends IMetadataSection> T getPackMetadata(
			MetadataSerializer metadataSerializer, String metadataSectionName)
					throws IOException {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage getPackImage() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPackName() {

		// TODO Auto-generated method stub
		return Reference.MOD_ID_GenBlocks;
	}

}
