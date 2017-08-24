package sample;

import org.jetbrains.tfsIntegration.checkin.PolicyBase;
import org.jetbrains.tfsIntegration.checkin.PolicyType;
import org.jetbrains.tfsIntegration.checkin.PolicyFailure;
import org.jetbrains.tfsIntegration.checkin.PolicyContext;
import org.jetbrains.annotations.NotNull;
import org.jdom.Element;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
// This implementation check that names of the files that are checked in match the pattern

public class CheckinCommentPolicy extends PolicyBase {

    // Let's give our policy an ID matching to ID of TFS built-in checkin policy. This way our implementation will
    // be triggered if this policy was enabled (using Team Explorer)
    private static final String ID = "CheckForCommentsPolicy.CheckForComments";
    private static final String DESCRIPTION = "Checks for non-empty checkin comment";
    private static final String INSTALLATION_INSTRUCTIONS = "This policy is part of CheckinPolicies plugin.";

    private static final PolicyType TYPE = new PolicyType(ID, "Changeset Comments Policy", DESCRIPTION, INSTALLATION_INSTRUCTIONS);

    @NotNull
    public PolicyType getPolicyType() {
        return TYPE;
    }

    public PolicyFailure[] evaluate(@NotNull PolicyContext policyContext, @NotNull ProgressIndicator progressIndicator) {
        if (policyContext.getCommitMessage() == null || policyContext.getCommitMessage().trim().length() == 0) {
            return new PolicyFailure[]{new PolicyFailure(this, "Check in comment is empty")};
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