package mcbe.boymelancholy.signeditkt.forms

import cn.nukkit.Player
import cn.nukkit.blockentity.BlockEntitySign
import itsu.mcbe.form.base.SimpleForm
import itsu.mcbe.form.element.Button
import mcbe.boymelancholy.signeditkt.SignEditKt
import mcbe.boymelancholy.signeditkt.utils.FormMaker
import mcbe.boymelancholy.signeditkt.utils.Memory

class PasteForm {
    fun getForm(player: Player): SimpleForm? {
        val memory: Memory = SignEditKt.instance.getMemory()
        val sign: BlockEntitySign = memory.getSignMemory(player)!!
        val copyKeys: Array<String> = (memory.getCopiedMemory(player))!!.keys.toTypedArray()
        val form: SimpleForm = object : SimpleForm() {
            override fun onEnter(player: Player, index: Int) {
                if (index == -1) {
                    return
                }
                val key = copyKeys[index]
                val lines: Array<String> = memory.getCopiedMemory(player, key)!!
                sign.setText(*lines)
                sign.saveNBT()
                player.sendMessage("> 貼り付けました")
            }
        }
        form.id = FormMaker.formCount++
        form.title = "PASTE"
        form.content = "貼り付けるデータを選択してください"
        (memory.getCopiedMemory(player))?.forEach { k, v ->
            form.addButton(Button().setText(k))
        }

        return form
    }
}