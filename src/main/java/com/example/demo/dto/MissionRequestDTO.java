package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class MissionRequestDTO {
    public static class AssignMissionToStoreDto {
        @NotNull
        private Long storeId;

        // Other fields relevant to the assignment

        public void setStoreId(Long storeId) {
            this.storeId = storeId;
        }

        public Long getStoreId() {
            return storeId;
        }
    }

    public static class changeMissionStatusDto {
        @NotNull
        private Long missionId;

        // Getter and Setter
        public Long getMissionId() {
            return missionId;
        }

        public void setMissionId(Long missionId) {
            this.missionId = missionId;
        }
    }
}
