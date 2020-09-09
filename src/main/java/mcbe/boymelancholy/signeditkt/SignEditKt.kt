package mcbe.boymelancholy.signeditkt

import cn.nukkit.plugin.PluginBase
import itsu.mcbe.form.core.NukkitFormAPI;
import mcbe.boymelancholy.signeditkt.utils.Memory

class SignEditKt: PluginBase() {

    private val memory: Memory = Memory()
    private val formApi = NukkitFormAPI.getSingleton()

    override fun onEnable() {
        instance = this
        this.server.pluginManager.registerEvents(EventListener(), this)
    }

    fun getMemory(): Memory {
        return memory
    }

    fun getFormAPI(): NukkitFormAPI {
        return formApi
    }

    companion object {
        lateinit var instance: SignEditKt
    }
}