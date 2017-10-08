package monstersanddungeons.packet;

import io.netty.buffer.ByteBuf;
import monstersanddungeons.entity.MaDEntityMonsterBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateClientEntityAnimation implements IMessage, IMessageHandler<UpdateClientEntityAnimation , IMessage> {

	int id, animationID, phase;
	boolean reset;

	public UpdateClientEntityAnimation(){ }


	public UpdateClientEntityAnimation(MaDEntityMonsterBase entity, int whichAnimation, int phase)
	{
		id = entity.getEntityId();
		this.reset = false;
		this.animationID = whichAnimation;
		this.phase = phase;
	}
	
	public UpdateClientEntityAnimation(MaDEntityMonsterBase entity, int whichAnimation, int phase, boolean reset)
	{
		id = entity.getEntityId();
		this.reset = reset;
		this.animationID = whichAnimation;
		this.phase = phase;
	}

	@Override
	public IMessage onMessage(final UpdateClientEntityAnimation message, MessageContext ctx) {

		IThreadListener mainThread = Minecraft.getMinecraft();
		mainThread.addScheduledTask(new Runnable() {

			@Override
			public void run() {
				EntityPlayer p = Minecraft.getMinecraft().player;
				World world = p.world;

				MaDEntityMonsterBase rook = (MaDEntityMonsterBase) world.getEntityByID(message.id);

				if(rook != null)
				{
					if(!message.reset)
					{
						rook.acivateAnimationby(message.animationID, message.phase);
					}else
					{
						rook.resetAnimation();
					}
				}
			}
		});


		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.animationID = buf.readInt();
		this.reset = buf.readBoolean();
		this.phase = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeInt(animationID);
		buf.writeBoolean(reset);
		buf.writeInt(phase);
	}
}