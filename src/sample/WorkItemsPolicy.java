package sample;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.tfsIntegration.checkin.PolicyBase;
import org.jetbrains.tfsIntegration.checkin.PolicyContext;
import org.jetbrains.tfsIntegration.checkin.PolicyFailure;
import org.jetbrains.tfsIntegration.checkin.PolicyType;
// This implementation check that names of the files that are checked in match the pattern

public class WorkItemsPolicy extends PolicyBase {

    // Let's give our policy an ID matching to ID of TFS built-in checkin policy. This way our implementation will
    // be triggered if this policy was enabled (using Team Explorer)
    private static final String ID = "Microsoft.TeamFoundation.VersionControl.Controls.WorkItemPolicy";
    private static final String DESCRIPTION = "Checks for no tasks checkins";
    private static final String INSTALLATION_INSTRUCTIONS = "This policy is part of CheckinPolicies plugin.";

    private static final PolicyType TYPE = new PolicyType(ID, "Work Items Policy", DESCRIPTION, INSTALLATION_INSTRUCTIONS);

    @NotNull
    public PolicyType getPolicyType() {
        return TYPE;
    }

    public PolicyFailure[] evaluate(@NotNull PolicyContext policyContext, @NotNull ProgressIndicator progressIndicator) {
        if (policyContext.getWorkItems() == null || policyContext.getWorkItems().size() == 0) {
            return new PolicyFailure[]{new PolicyFailure(this, "No Checkin Without Task")};
        }
        return NO_FAILURES;
    }

    public boolean canEdit() {
        return false;
    }

    public boolean edit(Project project) {
        return false;
    }

    public void loadState(@NotNull Element element) {
    }

    public void saveState(@NotNull Element element) {
    }
}