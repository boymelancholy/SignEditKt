package mcbe.boymelancholy.signeditkt.utils

import cn.nukkit.Player
import cn.nukkit.blockentity.BlockEntitySign

class Memory {
    private var makerMemory: MutableMap<Player, FormMaker> = mutableMapOf()
    private var signMemory: MutableMap<Player, BlockEntitySign> = mutableMapOf()
    private var copiedMemory: MutableMap<Player, MutableMap<String, Array<String>>?> = mutableMapOf()

    fun getMakerMemory(player: Player): FormMaker? {
        return makerMemory[player]
    }

    fun getSignMemory(player: Player): BlockEntitySign? {
        return signMemory[player]
    }

    fun getCopiedMemory(player: Player): MutableMap<String, Array<String>>? {
        return copiedMemory[player]
    }

    fun getCopiedMemory(player: Player, tag: String): Array<String>? {
        val map =  copiedMemory.get(player)
        if (map != null) {
            return if (map.containsKey(tag)) {
                map[tag]
            } else {
                arrayOf("", "", "", "")
            }
        }
        return arrayOf("", "", "", "")
    }

    fun setMakerMemory(player: Player, maker: FormMaker) {
        makerMemory[player] = maker
    }

    fun setSignMemory(player: Player, sign: BlockEntitySign) {
        signMemory[player] = sign
    }

    fun setCopiedMemory(player: Player, tag: String, texts: Array<String>) {
        val map = copiedMemory[player]
        map?.put(tag, texts)
        copiedMemory.replace(player, map)
    }

    fun hasCopiedMemory(player: Player): Boolean? {
        return copiedMemory.containsKey(player)
    }

    fun hasCopiedMemory(player: Player, tag: String): Boolean? {
        if (!this.copiedMemory.containsKey(player)) return false
        val map = copiedMemory[player]
        return map?.containsKey(tag)
    }

    fun initCopiedMemory(player: Player) {
        if (copiedMemory.containsKey(player)) return
        copiedMemory[player] = mutableMapOf()
    }

    fun removeCopiedMemory(player: Player, tag: String) {
        if (!copiedMemory.containsKey(player)) return
        (copiedMemory[player])?.remove(tag)
    }
}