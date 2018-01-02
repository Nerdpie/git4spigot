package io.github.nerdpie.git4spigot;

import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

// FIXME Determine our versioning scheme
@SuppressWarnings("unused")
public class git4spigot extends JavaPlugin {

    /** Planning
     *
     * Features:
     * Config file
     * Default list to ignore and version
     * Support for OAuth keys (try to reduce impact of compromised keys)
     * Mechanism to catalog plugins, including the version, namespace, and a hash
     *
     * Do we want a persistent repo reference, or should we create one each time we need to manipulate the repo?
     * Trade-offs include:
     *      system overhead for maintaining an instance versus periodic instantiation (depends on frequency, size of changes)
     *      Ensuring proper locking if we create a per-use reference (e.g. if one operation runs long, don't cause thread locking issues)
     *
     *
     * On initial load, the plugin should just generate a default config file.
     * It should not start versioning ANYTHING until given the command to do so; the command should require OP or a specific permission,
     *      unless we restrict it to use by the console only.  Given the nature of the plugin, that may be our best bet.
     * This will also allow server staff to configure the plugin before it starts working, such as setting up the base folder and the ignore patterns.
     * Once the repo has been initialized, we will either change a value in the config, or add a marker file, causing the plugin to
     *      automatically check the repo any time the server reboots.
     *
     * REFINE We may be able to hook into basic file IO calls to create auto-commits any time a config is changed from in-game
     * This would allow a great deal of control if malicious actors compromised the server, as each change could be reverted separately.
     *
     * TODO Adjust the .gitignore file and generate a user template
     * TODO Create a template for Spigot plugin projects
     * TODO Decide if we want to use the Spigot "plugin annotations" tool
     *
     */ //End Planning

    // TODO This will be set from the configs, allowing server owners to version different scopes and adjust for host nuances
    private File serverRoot = this.getDataFolder().getParentFile().getParentFile(); // This should take us to 'plugins', then the server root

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void setupGitRepo() {
        // TODO Move this to a separate wrapper class
        Git git = null;
        try {
            git = Git.init().setDirectory(serverRoot).setBare(false).call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }

        // TODO Verify that the repo was properly created, and that we already have our initial commit

        try {
            assert git != null;
            System.out.println(git.status().call().toString());
        } catch (GitAPIException e) {
            e.printStackTrace();
        }

    }

    private void gatherPluginMeta() {

    }

}
