package mcbe.boymelancholy.signeditkt.forms

import cn.nukkit.Player
import itsu.mcbe.form.base.SimpleForm
import itsu.mcbe.form.element.Button
import mcbe.boymelancholy.signeditkt.SignEditKt
import mcbe.boymelancholy.signeditkt.utils.FormMaker
import mcbe.boymelancholy.signeditkt.utils.Memory
import java.util.*


class DeleteForm {
    fun getForm(player: Player): SimpleForm? {
        val memory: Memory = SignEditKt.instance.getMemory()
        val copyKeys: Array<String> = (memory.getCopiedMemory(player))!!.keys.toTypedArray()
        val form: SimpleForm = object : SimpleForm() {
            override fun onEnter(player: Player, index: Int) {
                if (index == -1) {
                    player.sendMessage("> 中断しました")
                    return
                }
                val key = copyKeys[index]
                memory.removeCopiedMemory(player, key)
                player.sendMessage("> 削除しました")
            }
        }
        form.id = FormMaker.formCount++
        form.title = "DELETE"
        form.content = "削除するデータを選択してください"
        (memory.getCopiedMemory(player))?.forEach { k, v ->
            form.addButton(Button().setText(k))
        }
        return form
    }
}