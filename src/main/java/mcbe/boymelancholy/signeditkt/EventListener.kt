package mcbe.boymelancholy.signeditkt

import cn.nukkit.Player
import cn.nukkit.block.Block
import cn.nukkit.block.BlockSignPost
import cn.nukkit.blockentity.BlockEntity
import cn.nukkit.blockentity.BlockEntitySign
import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerInteractEvent
import cn.nukkit.item.ItemFeather
import cn.nukkit.level.Level
import cn.nukkit.math.Vector3
import mcbe.boymelancholy.signeditkt.utils.FormMaker
import mcbe.boymelancholy.signeditkt.utils.Memory


class EventListener: Listener {

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        val player: Player = event.player
        val block: Block = event.block
        val item = event.item
        if (block is BlockSignPost && item is ItemFeather) {
            val memory: Memory = SignEditKt.instance.getMemory()
            val level: Level = player.getLevel()
            val pos = Vector3(block.x, block.y, block.z)
            val entitySign: BlockEntity = level.getBlockEntity(pos) as? BlockEntitySign ?: return
            val maker = FormMaker(player)
            memory.setSignMemory(player, entitySign as BlockEntitySign)
            memory.setMakerMemory(player, maker)
            maker.sendForm(FormMaker.call)
        }
    }
}